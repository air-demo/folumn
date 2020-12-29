package com.air.folumn.advice;

import com.air.folumn.Exception.CustomSizeException;
import com.air.folumn.enums.CustomSizeErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author air
 * @create 2020-11-23-10:47
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model){
        if(ex instanceof CustomSizeException){
            model.addAttribute("message", ex.getMessage());
        }else {
            model.addAttribute("message", CustomSizeErrorCode.SYSTEM_ERROR);
        }
        return new ModelAndView("error");
    }

}
