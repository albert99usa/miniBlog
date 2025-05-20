package com.blog.blog.controller;

import com.blog.blog.entity.Reply;
import com.blog.blog.service.ArticleService;
import com.blog.blog.service.ReplyService;
import com.blog.blog.service.TopicService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author tangzhiqiang
 */
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    //@RequestMapping(value="/article/{topicID}")
    @GetMapping(value="/article/{topicID}")
    public String showArticle(@PathVariable("topicID")Long topicID, ModelMap model){
        topicService.increaseVisitCount(topicID);
        model.addAttribute("article",articleService.findArticleVo(topicID));
        return "article/show";
    }

    @RequestMapping(value="/article/{topicID}/reply/{replyID}/del")
    /*public String delReply(@PathVariable("topicID")String topicID,
                              @PathVariable("replyID")String replyID){*/
    public String delReply(@PathVariable("topicID")Long topicID,
                           @PathVariable("replyID")Long replyID){
        replyService.deleteReply(replyID,topicID);
        return "redirect:/article/"+topicID;
    }


    @RequestMapping(value = "/article/reply/{replyID}.json")
    @ResponseBody
    public Reply getReply(@PathVariable("replyID")Long replyID){
        return replyService.getReply(replyID);
    }



    @RequestMapping(value="/article/{topicID}/reply/{replyID}/edit")
    public String updateReply(@PathVariable("topicID")Long topicID,
                              @PathVariable("replyID")Long replyID,
                              @RequestParam("editormd-edit-markdown-doc")String contentMD,
                              @RequestParam("editormd-edit-html-code")String contentHTML,
                              RedirectAttributes redirectAttributes){
        if(StringUtils.isEmpty(contentMD)&&StringUtils.isEmpty(contentHTML)){
            redirectAttributes.addFlashAttribute("messageErr","評論内容不能為空！");
            return "redirect:/article/"+topicID;
        }

        Reply updatedReply=replyService.updateReplyContent(replyID,contentMD,contentHTML);
        if(null==updatedReply||updatedReply.getId()==null){
            redirectAttributes.addFlashAttribute("messageErr","評論修改失败！");
            return "redirect:/article/"+topicID;
        }else{
            redirectAttributes.addFlashAttribute("messageSuc","評論修改成功！");
            return "redirect:/article/"+topicID;
        }
    }
}
