package com.blog.blog.service.impl;

import com.blog.blog.entity.Reply;
import com.blog.blog.entity.Topic;
import com.blog.blog.service.ArticleService;
import com.blog.blog.service.ReplyService;
import com.blog.blog.service.TopicService;
import com.blog.blog.vo.ArticleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangzhiqiang
 */
@Service
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    @Override
    public ArticleVo findArticleVo(Long topicId) {
        Topic topic=topicService.findTopicById(topicId);
        List<Reply> replyList=replyService.findReplyByTopicId(topicId);
        ArticleVo vo=new ArticleVo();
        vo.setTopic(topic);
        vo.setReplyList(replyList);
        return vo;
    }
}
