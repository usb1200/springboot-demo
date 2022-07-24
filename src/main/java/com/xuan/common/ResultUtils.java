package com.xuan.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 返回工具类
 *
 * @author yupi
 */
@AllArgsConstructor
@Data
public class ResultUtils {
    private String code;
    private String msg;
    private Object data;


    public static ResultUtils success() {
        return new ResultUtils(ErrorCode.CODE_200, "", null);
    }

    public static ResultUtils success(Object data) {
        return new ResultUtils(ErrorCode.CODE_200, "", data);

    }

    public static ResultUtils error(String code, String msg) {
        return new ResultUtils(code, msg, null);
    }


    public static ResultUtils error() {
        return new ResultUtils(ErrorCode.CODE_500, "系统错误", null);
    }


}
