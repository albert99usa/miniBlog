package com.blog.blog.service;

import com.blog.blog.entity.Reply;
import com.blog.blog.vo.ReplyVo;

import java.util.List;

/**
 * @author tangzhiqiang
 */
public interface ReplyService {


    /**
     * 取得指定評論内容
     * @param replyID
     * @return
     */
    Reply getReply(Long replyID);


    /**
     * 新增評論
     * @param vo
     * @return
     */
    Reply addReply(ReplyVo vo);


    /**
     * 更新評論内容
     * @param replyId
     * @param contentMD
     * @param contentHTML
     * @return
     */
    Reply updateReplyContent(Long replyId, String contentMD, String contentHTML);


    /**
     * 查找指定文章的評論
     * @param topicId
     * @return
     */
    List<Reply> findReplyByTopicId(Long topicId);


    /**
     * 删除評論
     * @param replyId
     * @param topicId
     */
   // void deleteReply(String replyId, String topicId);
    void deleteReply(Long replyId, Long topicId);
}
