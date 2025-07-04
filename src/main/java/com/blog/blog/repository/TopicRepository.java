package com.blog.blog.repository;

import com.blog.blog.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 文章操作類
 * @author tangzhiqiang
 */
@Repository
//public interface TopicRepository extends PagingAndSortingRepository<Topic,String> {
public interface TopicRepository extends JpaRepository<Topic,Long> {

    /**
     * 查找指定用户的文章
     * @param authorId
     * @param pageable
     * @return
     */
    Page<Topic> findByAuthorId(Long authorId, Pageable pageable);

    /**
     * 查找指定用户的文章
     * @param authorName
     * @param pageable
     * @return
     */
    Page<Topic> findByAuthorName(String authorName, Pageable pageable);

    /**
     * 查找含有指定標籤的文章
     * @param tagName
     * @param pageable
     * @return
     */
    //Page<Topic> findByTagsContains(String tagName, Pageable pageable);

    /**
     * 按分類分類查找
     * @param catDir
     * @param pageable
     * @return
     */
    Page<Topic> findByCatDir(String catDir, Pageable pageable);

    /**
     * 標題模糊查询
     * @param title
     * @param pageable
     * @return
     */
    Page<Topic> findByTitleLike(String title, Pageable pageable);


    /**
     * 在標題標題或者摘要模糊查询
     * @param title
     * @param desc
     * @param pageable
     * @return
     */
    Page<Topic> findByTitleLikeOrDescriptionLike(String title, String desc, Pageable pageable);


    /**
     * 指定用户收藏的所有文章
     * @param userId
     * @param pageable
     * @return
     */
    Page<Topic> findByCollectedUsersContains(Long userId, Pageable pageable);

    /**
     * 在標題標題或者摘要模糊查询
     * @param words 关键字
     * @param page
     * @return
     */
    //@Query(value = "{ $or: [ { 'title' : {$regex:?0,$options:'i'} }, { 'desc' : {$regex:?0,$options:'i'} } ] }")
   // @Query(value = "{ $or: [ { 'title' : {$regex:?0,$options:'i'} }, { 'desc' : {$regex:?0,$options:'i'} } ] }")
   // Page<Topic> likeQuery(String words, Pageable page);

}
