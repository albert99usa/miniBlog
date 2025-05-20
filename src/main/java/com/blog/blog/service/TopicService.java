package com.blog.blog.service;

import com.blog.blog.entity.Topic;
import com.blog.blog.vo.IndexVo;
import com.blog.blog.vo.SearchVo;
import com.blog.blog.vo.TopicVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author tangzhiqiang
 */
public interface TopicService {

    /**
     * 分頁查找
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByPage(int pageNo, int pageSize);

    /**
     * 指定用户文章
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByUserIdAndPage(Long userId, int pageNo, int pageSize);

    /**
     * 指定用户創建的文章
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByUsernameAndPage(String username, int pageNo, int pageSize);

    /**
     * 含有指定標籤的文章
     * @param tagName
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findByTagAndPage(String tagName, int pageNo, int pageSize);

    /**
     * 首頁分頁查找
     * @param vo
     * @return
     */
    Page<Topic> findByPage(IndexVo vo);


    /**
     * 关键字分頁查找
     * @param searchVo
     * @return
     */
    Page<Topic> search(SearchVo searchVo);


    /**
     * 新增文章
     * @param vo
     * @return
     */
    Topic addTopic(TopicVo vo);


    /**
     * 查找指定文章
     * @param topicId
     * @return
     */
    Topic findTopicById(Long topicId);


    /**
     * 查找指定文章,并转换成vo類
     * @param topicId
     * @return
     */
    TopicVo findTopicVoById(Long topicId);


    /**
     * 更新文章信息
     * @param vo
     * @param id
     * @return
     */
    Topic updateById(TopicVo vo, Long id);


    /**
     * 删除文章
     * @param topicId
     */
    void deleteById(Long topicId);

    /**
     * 更新访问次数
     * @param topicId
     */
    void increaseVisitCount(Long topicId);

    /**
     * 更新評論次数
     * @param topicId
     */
    void increaseReplyCount(Long topicId);

    /**
     * 减少評論次数
     * @param topicId
     */
    void decreaseReplyCount(Long topicId);

    /**
     * 收藏文章
     * @param topicId
     * @param userId
     * @return
     */
    Topic addCollection(Long topicId, Long userId);


    /**
     * 取消收藏
     * @param topicId
     * @param userId
     * @return
     */
    Topic removeCollection(Long topicId, Long userId);

    /**
     * 喜欢文章
     * @param topicId
     * @param userId
     * @return
     */
    Topic like(Long topicId, Long userId);


    /**
     * 不喜欢收藏
     * @param topicId
     * @param userId
     * @return
     */
    Topic unLike(Long topicId, Long userId);


    /**
     * 用户收藏的所有文章
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Topic> findCollectedTopicsByUidAndPage(Long userId, int pageNo, int pageSize);

    /**
     * 查找所有文章
     * @return
     */
    List<Topic> findAll();
}
