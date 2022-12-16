package com.gyk.myauction.utils;

import com.gyk.myauction.entity.Auctionuser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    //返回ture就放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断是否有登录  根据session的loginuser来判断
        HttpSession session = request.getSession();
        Auctionuser loginuser = (Auctionuser) session.getAttribute("loginUser");

        if (loginuser!=null){
            //有登录
            return true;
        }else {
            //没有登录
            //重新登录
            response.sendRedirect("login");
            return false;
        }
    }
}
