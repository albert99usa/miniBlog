package com.blog.blog.service;

import com.blog.blog.dto.RegistrationDto;
import com.blog.blog.entity.User;
import com.blog.blog.vo.RegisterUserVo;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

     User findByEmail(String email);

    /**
     * 創建用户
     * @param vo
     * @return
     */
    User createUser(RegisterUserVo vo);


    /**
     * 獲取用户
     * @param uid
     * @return
     */
    User getUser(Long uid);

    /**
     * 根据用户名和密碼查找用户
     * @param username
     * @param password
     * @return
     */
    User findUser(String username, String password);

    /**
     * 判断用户是否有效
     * @param username
     * @param password
     * @return
     */
    boolean isUserValid(String username, String password);


    /**
     * 使用用户名查找用户
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 使用 郵箱查找用户
     * @param email
     * @return
     */
    User findUserByEmail(String email);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    User updateUserInfo(User user);


    /**
     * 修改用户密碼
     * @param userId
     * @param newPwd
     * @return
     */
    User updatePwd(Long userId, String newPwd);

    /**
     * 更新頭像
     * @param userId
     * @param avatarURL
     * @param isUploaded 是否通过上傳更新的頭像圖片
     * @return
     */
    User updateAvatar(Long userId, String avatarURL, boolean isUploaded);
}
