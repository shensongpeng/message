package com.example.message.message.handler;

import com.example.message.message.exception.UserAuthorizeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by wwd
 */
@ControllerAdvice
public class MessageExceptionHandler {

    //拦截登录异常
    @ExceptionHandler(value = UserAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("common/loginView");
    }
}
