package com.gyk.myauction.controller;

import ch.qos.logback.core.joran.spi.ActionException;
import com.gyk.myauction.utils.AuctionPriceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyException {

    //当出现ActionPriceException时会执行这个方法
    @ExceptionHandler(AuctionPriceException.class)
    public ModelAndView priceException(@NotNull Exception e){
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",e.getMessage());
        mv.setViewName("error");
        return mv;
    }
}
