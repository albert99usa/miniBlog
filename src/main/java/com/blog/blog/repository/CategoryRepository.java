package com.blog.blog.repository;

import com.blog.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分類文件操作類
 * @author tangzhiqiang
 */
@Repository
//public interface CategoryRepository extends PagingAndSortingRepository<Category,String> {
public interface CategoryRepository extends JpaRepository<Category,Long> {

    /**
     * 目錄名字必须唯一
     * @param catDir 分類英文目錄名稱
     * @return
     */
    List<Category> findByCatDir(String catDir);

}
