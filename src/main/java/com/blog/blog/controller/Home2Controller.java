package com.blog.blog.controller;

import com.blog.blog.entity.User;
import com.blog.blog.service.CategoryService;
import com.blog.blog.service.TopicService;
import com.blog.blog.service.UserService;
import com.blog.blog.utils.CommonProps;
import com.blog.blog.utils.ValidateCode;
import com.blog.blog.vo.IndexVo;
import com.blog.blog.vo.LoginUserVo;
import com.blog.blog.vo.RegisterUserVo;
import com.blog.blog.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 首頁控制器
 * @author tangzhiqiang
 */
@Controller
@Slf4j
public class Home2Controller {

    public static final String VCODE_SESSION_KEY="validateCode";
/*

    @Value("${appname}")
    private String configAppName;
*/

    @Autowired
    private UserService userService;


    @Autowired
   private TopicService topicService;
//
//    @Autowired
//    private CategoryService categoryService;


    /**
     * 跳转到首頁
     * @param vo 首頁参数封装
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/")
    public String home(IndexVo vo, ModelMap modelMap) {
       modelMap.addAttribute("pager",topicService.findByPage(vo));
      //  modelMap.addAttribute("catList",categoryService.findAll());
      //  modelMap.addAttribute("indexVo",vo);
        log.info("vo.toString()--"+vo.toString());
        return "index";
    }

    @RequestMapping(value="/search")
    public String search(SearchVo searchVo, ModelMap modelMap){
        modelMap.addAttribute("searchVo",searchVo);
     //   modelMap.addAttribute("pager",topicService.search(searchVo));
        return "search";
    }

    /**
     * 註冊頁面
     * @return
     */
  @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("registerForm",new RegisterUserVo());
        return "register";
    }


    /**
     * 用户登錄
     * @return
     */
  @RequestMapping(value="/register",method = RequestMethod.POST)
    public String doegister(@Valid @ModelAttribute("registerForm") RegisterUserVo registerUser, BindingResult result,
                            HttpSession session,
                            ModelMap model,
                            RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "register";
        }

        String vcodeInSession = (String) session.getAttribute(VCODE_SESSION_KEY);
        String submitCode = registerUser.getValidateCode();
        if (!StringUtils.equals(vcodeInSession,submitCode)) {
            result.rejectValue("validateCode",null,"驗證碼錯誤!");
      }

        if(null!=userService.findByUsername(registerUser.getUsername())){
            result.rejectValue("username",null,"該用户名已經存在");
        }
        if(null!=userService.findUserByEmail(registerUser.getEmail())){
            result.rejectValue("email",null,"該 郵箱已經被註冊");
        }
        if(result.hasErrors()){
            return "register";
        }

        User savedUser=userService.createUser(registerUser);
        if(null!=savedUser&&savedUser.getId()!=null){
            redirectAttributes.addFlashAttribute("messageSuc","註冊成功！");
            return "redirect:/login";
        }else{
            model.addAttribute("messageErr","註冊失败");
            model.addAttribute("vo",registerUser);
            return "register";
        }
        //return "";
    }



    /**
     * 登錄頁面
     * @return
  /*   *//*
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(ModelMap model) {
        model.addAttribute("loginForm",new LoginUserVo());
        return "login";
    }

    *//**
     * 用户登錄
     * @return
     *//*
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("loginForm") LoginUserVo user, BindingResult result,
                          HttpSession session,
                          ModelMap model,
                          RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            return "login";
        }

        String vcodeInSession = (String) session.getAttribute(VCODE_SESSION_KEY);
        String submitCode = user.getValidateCode();

        if (!StringUtils.equals(vcodeInSession,submitCode)) {
            result.rejectValue("validateCode",null,"驗證碼錯誤!");
            return "login";
        }
      *//*  if(!userService.isUserValid(user.getUsername(),user.getPassword())){
            model.addAttribute("messageErr","用户名或者密碼錯誤");
            return "login";
        }*//*

       // session.setAttribute(CommonProps.LOGIN_USER_SESSION_KEY,userService.findUser(user.getUsername(),user.getPassword()));
        return "redirect:/";
    }

*/

    /**
     * 生成驗證碼
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-cache");
        String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_ALL_MIXED, 4, null);
        request.getSession().setAttribute(VCODE_SESSION_KEY, verifyCode);
        response.setContentType("image/jpeg");
        BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
        ImageIO.write(bim, "JPEG", response.getOutputStream());
    }


    /**
     * 退出系统
     * @param session
     * @return
     * @throws Exception
     */
   /* @RequestMapping(value="/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }*/

    /**
     * 關於頁面
     * @return
     */
    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }


}
