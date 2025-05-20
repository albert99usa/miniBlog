package com.blog.blog.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


/**
 * @author tangzhiqiang
 */
@Getter
@Setter
public class RegisterUserVo {

    @NotEmpty(message = "用户名不能為空")
    private String username;

    @NotEmpty(message = "密碼不能為空")
    @Size(min = 6, max = 15, message = "密碼必须在6到15個字符")
    private String password;

    @NotEmpty(message = " 郵箱不能為空")
    @Email(message = " 郵箱格式不正确")
    private String email;

    @NotEmpty(message = "驗證碼不能為空")
    private String validateCode;
}
