package com.mtd.common;

import com.mtd.common.utils.MsgException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Written by Wooce Yang on 2015/12/29.
 */
public class ExceptionHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        if(MsgException.class.isInstance(ex)){
            request.setAttribute("msg", ex.getMessage());
            ModelAndView msgView = new ModelAndView("msg");
            return msgView;
        }
        else {
            ex.printStackTrace();
            return new ModelAndView("error");
        }
    }
}
