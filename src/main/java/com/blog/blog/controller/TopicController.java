package com.blog.blog.controller;

import com.blog.blog.entity.Topic;
import com.blog.blog.entity.User;
import com.blog.blog.repository.UserRepository;
import com.blog.blog.service.CategoryService;
import com.blog.blog.service.TopicService;
import com.blog.blog.utils.CommonProps;
import com.blog.blog.vo.PageVo;
import com.blog.blog.vo.TopicVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.http.HttpServletRequest;

import java.security.Principal;

/**
 * 文章控制器
 * @author tangzhiqiang
 */
@Controller
@RequestMapping("/topic")
public class TopicController {


    @Autowired
    private TopicService topicService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserRepository userRepository;


    /**
     * 文章列表
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String listTopics(PageVo pageVo, ModelMap model, HttpServletRequest request){
        /*model.addAttribute("pager", topicService.findByUsernameAndPage(
                ((User)(WebUtils.getSessionAttribute(request, CommonProps.LOGIN_USER_SESSION_KEY))).getUsername(),
                 pageVo.getPageNO(),
                 pageVo.getPageSize()));*/
        String currentUserName="";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
            //return currentUserName;
        }

        model.addAttribute("pager", topicService.findByUsernameAndPage(
                currentUserName.toString(),
                pageVo.getPageNO(),
                pageVo.getPageSize()));

        return "topic/topic_list";
    }

    /**
     * 新增文章表單
     * @return
     */
    @RequestMapping(value="/create",method = RequestMethod.GET)
    public String createTopic(@AuthenticationPrincipal UserDetails userDetails, ModelMap model){
        User loginuser = userRepository.findByEmail(userDetails.getUsername());
        model.addAttribute("loginUser",loginuser);
        model.addAttribute("catList",categoryService.findAll());
        return "topic/topic_add";
    }


    /**
     * 新增文章
     * @param vo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String doCreateTopic(TopicVo vo,
                                   ModelMap model,
                                   RedirectAttributes redirectAttributes){
        if(null==vo|| StringUtils.isEmpty(vo.getCatId().toString())
                ||StringUtils.isEmpty(vo.getTitle())
                ||StringUtils.isEmpty(vo.getContentMD())){
            model.addAttribute("messageErr","文章分類，標題，内容不能為空");
            model.addAttribute("catList",categoryService.findAll());
            model.addAttribute("topicVo",vo);
            return "topic/topic_add";
        }

        Topic savedTopic=topicService.addTopic(vo);
        if(null!=savedTopic&&savedTopic.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","文章創建成功");
            return "redirect:/topic/list";
        }else{
            redirectAttributes.addFlashAttribute("messageErr","文章創建失败");
            model.addAttribute("catList",categoryService.findAll());
            model.addAttribute("topicVo",vo);
            return "topic/topic_add";
        }
        
    }


    /**
     * 修改文章表單
     * @param topicID
     * @param model
     * @return
     */
    @RequestMapping(value="/edit/{topicID}",method = RequestMethod.GET)
    public String editTopic(@PathVariable("topicID") Long topicID, ModelMap model){
        model.addAttribute("topicVo",topicService.findTopicVoById(topicID));
        model.addAttribute("catList",categoryService.findAll());
        return "topic/topic_edit";
    }


    /**
     * 修改文章
     * @param vo
     * @param model
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/edit/{topicID}",method = RequestMethod.POST)
    public String doEditCategory(@PathVariable("topicID") Long topicID, TopicVo vo,
                                 ModelMap model,
                                 RedirectAttributes redirectAttributes){

        if(null==vo|| StringUtils.isEmpty(vo.getCatId().toString())
                ||StringUtils.isEmpty(vo.getTitle())
                ||StringUtils.isEmpty(vo.getContentMD())){
            model.addAttribute("messageErr","文章分類，標題，内容不能為空");
            model.addAttribute("topicVo",vo);
            return "topic/topic_edit";
        }

        Topic savedTopic=topicService.updateById(vo,topicID);
        if(null!=savedTopic&&savedTopic.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","文章更新成功");
            return "redirect:/topic/edit/"+savedTopic.getId();
        }else{
            redirectAttributes.addFlashAttribute("messageErr","文章更新失败");
            model.addAttribute("topicVo",vo);
            return "topic/topic_edit";
        }
    }



    /**
     * 删除文章
     * @param topicID
     * @return
     */
    @RequestMapping(value="/del/{topicID}",method = RequestMethod.GET)
    public String delTopic(@PathVariable("topicID") Long topicID, RedirectAttributes redirectAttributes){
        topicService.deleteById(topicID);
        redirectAttributes.addFlashAttribute("messageSuc","文章删除成功");
        return "redirect:/topic/list";
    }

}
