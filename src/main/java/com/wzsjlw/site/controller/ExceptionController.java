package com.wzsjlw.site.controller;

import com.wzsjlw.site.utils.ResultUtil;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sun.applet.Main;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: ll
 * @version: 1.0 2019-08-15
 * @see:
 * @since:
 */
@RestControllerAdvice
public class ExceptionController {
    /**
     * 捕捉 shiro 异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResultUtil handler401(ShiroException e) {
        return ResultUtil.error(e.getMessage());
    }

    /**
     * 捕捉 unauthorizedException 异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthenticatedException.class)
    public ResultUtil handler401() {
        return ResultUtil.error("未授权");
    }

    /**
     * 捕捉其它所有异常
     *
     * @param request
     * @param ex
     * @return 异常处理结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResultUtil globalException(HttpServletRequest request, Throwable ex) {
        return ResultUtil.error(getStatus(request).value(), ex.getMessage());
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
