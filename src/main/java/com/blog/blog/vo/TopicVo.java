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
public class TopicVo{

    private Long topicId;
    private Long catId;
    private String title;
    private String desc;
    private String thumbURL;
    private String tags;
    private String contentMD;
    private String contentHTML;
    private Long authorId;
    private String authorName;
    private boolean top;
    private boolean good;
    private boolean contentIsHTML;


}
