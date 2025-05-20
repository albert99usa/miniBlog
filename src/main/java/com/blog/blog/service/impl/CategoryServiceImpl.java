package com.blog.blog.service.impl;

import com.blog.blog.entity.Category;
import com.blog.blog.repository.CategoryRepository;
import com.blog.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * @author tangzhiqiang
 */
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public boolean isCategoryExisted(String catDir) {
        List<Category> catList=categoryRepository.findByCatDir(catDir);
        if(null!=catList&&!catList.isEmpty()){
            return true;
        }
        return false;
    }



    @Override
    public Category addCategory(Category cat) {
      return  categoryRepository.save(cat);
    }



    @Override
    //public Category findById(String id) {
    public Category findById(Long id) {
        Optional<Category> optional=categoryRepository.findById( id );
        return optional.isPresent()?optional.get():null;
    }

    /**
     * 注意：这里Page的页码是从0开始，所以取值需要传递过来的页码减去1
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Category> findByPage(int pageNo, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createAt");
      //  Pageable pageable =PageRequest.of(pageNo-1, pageSize, sort);
        Pageable pageable =PageRequest.of(pageNo-1, pageSize);
        return categoryRepository.findAll(pageable);
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>)categoryRepository.findAll();
    }

    @Override
    //public Category updateById(Category cat, String id) {
    public Category updateById(Category cat, Long id) {
        Category catInDB=findById(id);
        if(null==catInDB){
            return null;
        }
        cat.setId(id);
        cat.setCreateAt(catInDB.getCreateAt()==null?new Date():catInDB.getCreateAt());
        return categoryRepository.save(cat);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
