package com.xuan.exception;

/**
 * FileName: ServiceException.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.07.24
 */

import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
