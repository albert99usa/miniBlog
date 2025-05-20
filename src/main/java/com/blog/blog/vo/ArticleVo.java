package com.blog.blog.vo;

import com.blog.blog.entity.Reply;
import com.blog.blog.entity.Topic;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tangzhiqiang
 */
@Getter
@Setter
@ToString
public class ArticleVo {
    private Topic topic;
    private List<Reply> replyList;
}
