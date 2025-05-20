package com.blog.blog.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
public class LoginUserVo {

    @NotEmpty(message = "用户名不能為空")
    private String username;

    @NotEmpty(message = "密碼不能為空")
    private String password;

    @NotEmpty(message = "驗證碼不能為空")
    private String validateCode;
}
