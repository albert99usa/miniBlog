package com.blog.blog.service;

import com.blog.blog.vo.ArticleVo;

/**
 * @author tangzhiqiang
 */
public interface ArticleService {


    /**
     * 找到指定文章
     * @param topicId
     * @return
     */
    ArticleVo findArticleVo(Long topicId);



}
