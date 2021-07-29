package com.webbdong.security.controller.advice;//package com.itheima.controller.advice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionAdvice {

    /**
     * 只有出现 AccessDeniedException 异常才调转 403.jsp 页面
     */
    @ExceptionHandler(AccessDeniedException.class)
    public String exceptionAdvice() {
        return "forward:/403.jsp";
    }

}
