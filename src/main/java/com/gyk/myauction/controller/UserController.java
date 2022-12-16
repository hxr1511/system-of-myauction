package com.gyk.myauction.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.date.CalendarUtil;
import com.gyk.myauction.entity.Auctionuser;
import com.gyk.myauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    CircleCaptcha circleCaptcha;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/doLogin")
    public String doLogin(String username, String password,String code, Model model, HttpSession session){
        //判断验证码是否正确
        if (!circleCaptcha.verify(code)){
            //  验证码错误直接返回login
            //返回一个提示信息
            model.addAttribute("msg","验证码错误");
            return "login";
        }

        Auctionuser loginUser = userService.login(username,password);

        //判断不为空
        if (loginUser !=null){
            //登录成功
            System.out.println(loginUser+"登录成功");
            session.setAttribute("loginUser",loginUser);
            return "redirect:/findAuction";
        }else {
            //登录失败
            //返回一个提示信息
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }


    @RequestMapping("/getCode")
    public void getCode(HttpServletResponse response) throws IOException {
        circleCaptcha = CaptchaUtil.createCircleCaptcha(200,100,4,20);
        ServletOutputStream outputStream = response.getOutputStream();
        circleCaptcha.write(outputStream);
        System.out.println("Code:"+outputStream.toString());
        outputStream.close();
    }

    /**
     * 退出
     */
    @RequestMapping("/doLoginOut")
    public String doLoginOut(HttpSession session){
        session.removeAttribute("loginUser");
        return "login";
    }
}
