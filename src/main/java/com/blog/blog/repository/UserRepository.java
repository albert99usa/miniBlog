package com.blog.blog.repository;

import com.blog.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   // User findByEmail(String email);

    /**
     * 用户名查找用户
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     *  郵件查找用户
     *
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * 用户名就、密碼查找
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);


}
