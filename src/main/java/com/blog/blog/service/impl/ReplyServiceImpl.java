package com.blog.blog.service.impl;

import com.blog.blog.entity.Reply;
//import com.blog.blog.entity.embed.ReplyAuthorInfo;
import com.blog.blog.repository.ReplyRepository;
import com.blog.blog.service.ReplyService;
import com.blog.blog.service.TopicService;
import com.blog.blog.vo.ReplyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author tangzhiqiang
 */
@Service
public class ReplyServiceImpl implements ReplyService {


    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private TopicService topicService;

    @Override
    public Reply getReply(Long replyID) {
        Optional<Reply> optional=replyRepository.findById(replyID);
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public Reply addReply(ReplyVo vo) {
        Reply savedReply=replyRepository.save(convertVoToReply(vo));
        if(null!=savedReply&&savedReply.getId()!=null){
            //更新帖子评论次数
            topicService.increaseReplyCount(savedReply.getTopicId());
            return savedReply;
        }
        return null;
    }

    @Override
    public Reply updateReplyContent(Long replyId, String contentMD, String contentHTML) {
        Reply replyInDB=getReply(replyId);
        if(null==replyInDB){
            return null;
        }
        replyInDB.setContentMD(contentMD);
        replyInDB.setContentHTML(contentHTML);
        replyInDB.setUpdateAt(new Date());
        return replyRepository.save(replyInDB);
    }


    private Reply convertVoToReply(ReplyVo vo){
        if(null==vo){
            return null;
        }
        Reply reply=new Reply();
        reply.setContentMD(vo.getContentMD());
        reply.setContentHTML(vo.getContentHTML());
        reply.setTopicId(vo.getTopicId());
       // ReplyAuthorInfo authorInfo=new ReplyAuthorInfo();
       // authorInfo.setAuthorId(vo.getAuthorId());
       // authorInfo.setAuthorName(vo.getAuthorName());
      //  authorInfo.setAuthorAvatar(vo.getAuthorAvatar());
      //  reply.setAuthorInfo(authorInfo);
        reply.setReplyId(null);
        reply.setContentIsHTML(Boolean.TRUE);
        reply.setThumbsUPCount(0);
        reply.setDeleted(Boolean.FALSE);
        reply.setCreateAt(new Date());
        reply.setUpdateAt(new Date());
        return reply;
    }

    @Override
    public List<Reply> findReplyByTopicId(Long topicId) {
        return replyRepository.findAllByTopicId(topicId);
    }

    @Override
    //public void deleteReply(String replyId,String topicId) {
    public void deleteReply(Long replyId,Long topicId) {
        replyRepository.deleteById(replyId);
        topicService.decreaseReplyCount(topicId);
    }
}
