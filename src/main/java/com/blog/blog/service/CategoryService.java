package com.blog.blog.service;

import com.blog.blog.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author tangzhiqiang
 */
public interface CategoryService {

    /**
     * 新增分類
     * @param cat
     * @return
     */
    Category addCategory(Category cat);


    /**
     * 使用分類ID找到分類信息
     * @param id
     * @return
     */
   // Category findById(String id);
    Category findById(Long id);

    /**
     * 分頁查找
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Category> findByPage(int pageNo, int pageSize);

    /**
     * 找到所有分類信息
     * @return
     */
    List<Category> findAll();

    /**
     * 判断分類是否已經存在
     * @param catDir
     * @return
     */
    boolean isCategoryExisted(String catDir);


    /**
     * 更新分類信息
     * @param cat
     * @param id
     * @return
     */
    Category updateById(Category cat, Long id);


    /**
     * 删除指定分類
     * @param id
     * //TODO 删除分類同时也要删除該分類下文章
     */
    void deleteCategory(Long id);
}
