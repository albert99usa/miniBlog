package com.blog.blog.vo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class ReplyVo {

    private String contentMD;
    private String contentHTML;
    private Long topicId;
    private String authorId;
    private String authorName;
    private String authorAvatar;

}
