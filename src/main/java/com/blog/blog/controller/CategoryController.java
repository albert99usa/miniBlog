package com.blog.blog.controller;

import com.blog.blog.entity.Category;
import com.blog.blog.service.CategoryService;
import com.blog.blog.vo.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * 分類控制器
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/cat")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 分類列表
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String listCategory(PageVo pageVo, ModelMap model){
        model.addAttribute("pager",categoryService.findByPage(pageVo.getPageNO(),pageVo.getPageSize()));
        return "category/cat_list";
    }


    /**
     * 新增分類表單
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.GET)
    public String createCategory(){
        return "category/cat_add";
    }

    /**
     * 新增分類
     * @param cat
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String doCreateCategory(Category cat,
                                   ModelMap model,
                                   RedirectAttributes redirectAttributes){
        if(null==cat||StringUtils.isEmpty(cat.getCatName())||StringUtils.isEmpty(cat.getCatDir())){
            model.addAttribute("messageErr","分類名稱和目錄名不能為空");
            model.addAttribute("cat",cat);
            return "category/cat_add";
        }

        if(categoryService.isCategoryExisted(cat.getCatDir())){
            model.addAttribute("messageErr","目錄名已經存在,请换成其他的");
            model.addAttribute("cat",cat);
            return "category/cat_add";
        }

        cat.setCreateAt(new Date());
        cat.setUpdateAt(new Date());
        Category savedCat=categoryService.addCategory(cat);
        if(null!=savedCat&&savedCat.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","創建分類成功");
            return "redirect:/cat/list";
        }else{
            redirectAttributes.addFlashAttribute("messageErr","分類創建失败");
            model.addAttribute("cat",cat);
            return "category/cat_add";
        }
    }


    /**
     * 修改分類表單
     * @param catID
     * @param model
     * @return
     */
    @RequestMapping(value="/edit/{catID}",method = RequestMethod.GET)
    public String editCategory(@PathVariable("catID") Long catID, ModelMap model){
        model.addAttribute("cat",categoryService.findById(catID));
        return "category/cat_edit";
    }


    /**
     * 修改分類
     * @param cat
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/edit/{catID}",method = RequestMethod.POST)
    public String doEditCategory(@PathVariable("catID") Long catID, Category cat,
                                 ModelMap model,
                                 RedirectAttributes redirectAttributes){
        if(null==cat||StringUtils.isEmpty(cat.getCatName())||StringUtils.isEmpty(cat.getCatDir())){
            model.addAttribute("messageErr","分類名稱和目錄名不能為空");
            model.addAttribute("cat",cat);
            return "category/cat_edit";
        }

        cat.setUpdateAt(new Date());
        Category savedCat=categoryService.updateById(cat,catID);
        if(null!=savedCat&&savedCat.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","更新分類成功");
            return "redirect:/cat/list";
        }else{
            redirectAttributes.addFlashAttribute("messageErr","分類更新失败");
            model.addAttribute("cat",cat);
            return "category/cat_edit";
        }
    }

}
