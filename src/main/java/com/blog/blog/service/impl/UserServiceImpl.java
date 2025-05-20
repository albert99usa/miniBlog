package com.blog.blog.service.impl;

import com.blog.blog.dto.RegistrationDto;
import com.blog.blog.entity.Role;
import com.blog.blog.entity.User;
import com.blog.blog.repository.RoleRepository;
import com.blog.blog.repository.UserRepository;
import com.blog.blog.service.UserService;
import com.blog.blog.utils.GravatarUtils;
import com.blog.blog.vo.RegisterUserVo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder  =passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getFirstName()+" "+registrationDto.getLastName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role= roleRepository.findByName("ROLE_GUEST");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }



    @Override
    public User createUser(RegisterUserVo vo) {
        if(null==vo){
            return null;
        }
        User user=new User();
        user.setUsername(vo.getUsername());
        user.setPassword(DigestUtils.md5DigestAsHex(vo.getPassword().getBytes()));
        user.setEmail(vo.getEmail());
        user.setAvatarURL(GravatarUtils.makeGravatar(vo.getEmail()));
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long uid) {
        Optional<User> optional=userRepository.findById(uid);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public User findUser(String username, String password) {
        String encrypedPwd= DigestUtils.md5DigestAsHex(password.getBytes());
        return userRepository.findByUsernameAndPassword(username,encrypedPwd);
    }

    @Override
    public boolean isUserValid(String username, String password) {
        if(ObjectUtils.isEmpty(username)|| ObjectUtils.isEmpty(password)){
            return false;
        }

        User user=findUser(username,password);
        if(null!=user){
            return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUserInfo(User user) {
        if(null==user||user.getId()==null){
            return null;
        }
        User userInDB=this.getUser(user.getId());
        if(null==userInDB){
            return null;
        }
        user.setUsername(userInDB.getUsername());
        user.setPassword(userInDB.getPassword());
        user.setAvatarURL(userInDB.getAvatarURL());
        if(null==user.getEmail()){
            user.setEmail(userInDB.getEmail());
        }
        user.setCreateAt(userInDB.getCreateAt());
        user.setUpdateAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public User updatePwd(Long userId, String newPwd) {
        User userInDb=getUser(userId);
        if(null==userInDb){
            return null;
        }
        String encrypedNewPwd= DigestUtils.md5DigestAsHex(newPwd.getBytes());
        userInDb.setPassword(encrypedNewPwd);
        return userRepository.save(userInDb);
    }

    @Override
    public User updateAvatar(Long userId, String avatarURL, boolean isUploaded) {
        User userInDb=getUser(userId);
        if(null==userInDb){
            return null;
        }
        userInDb.setAvatarURL(avatarURL);
        userInDb.setAvatarURLByUploaded(isUploaded);
        return userRepository.save(userInDb);
    }
}


