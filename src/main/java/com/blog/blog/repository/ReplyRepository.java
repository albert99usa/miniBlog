package com.blog.blog.repository;

import com.blog.blog.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 评论操作类
 * @author tangzhiqiang
 */
@Repository
//public interface ReplyRepository extends PagingAndSortingRepository<Reply,String> {
public interface ReplyRepository extends JpaRepository<Reply,Long> {

    /**
     * 查找文章的所有评论
     * @param topicId
     * @return
     */
    List<Reply> findAllByTopicId(Long topicId);

    Optional<Reply>  findById(Long replyID);
}
