package com.blog.blog.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class AvatarVo {

    private String uid;

    @NotEmpty(message = "頭像文件不能為空")
    private MultipartFile file;

}
