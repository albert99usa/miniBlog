package com.blog.blog.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class UserPwdVo {

    private Long uid;
    private String username;

    @NotEmpty(message = "舊密碼不能為空")
    private String oldPwd;

    @NotEmpty(message = "新密碼不能為空")
    @Size(min = 6, max = 15, message = "新密碼必须在6到15個字符")
    private String newPwd;

    @NotEmpty(message = "新密碼不能為空")
    private String repeatNewPwd;
}
