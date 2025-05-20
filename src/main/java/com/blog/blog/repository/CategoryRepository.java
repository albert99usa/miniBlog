package com.blog.blog.repository;

import com.blog.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 栏目文档操作类
 * @author tangzhiqiang
 */
@Repository
//public interface CategoryRepository extends PagingAndSortingRepository<Category,String> {
public interface CategoryRepository extends JpaRepository<Category,Long> {

    /**
     * 目录名字必须唯一
     * @param catDir 栏目英文目录名称
     * @return
     */
    List<Category> findByCatDir(String catDir);

}
