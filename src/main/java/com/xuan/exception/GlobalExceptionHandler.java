package com.xuan.exception;

import com.xuan.common.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FileName: GlobalExceptionHandler.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.07.24
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 如果抛出的的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return Result
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResultUtils handle(ServiceException se){
        return ResultUtils.error(se.getCode(), se.getMessage());
    }
}
