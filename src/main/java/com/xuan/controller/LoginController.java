package com.xuan.controller;

import com.xuan.common.ErrorCode;
import com.xuan.common.ResultUtils;
import com.xuan.entity.UserVo;
import com.xuan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: LoginController.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.07.23
 */

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResultUtils login(@RequestBody UserVo userVo){
        if (userVo == null){
            return ResultUtils.error(ErrorCode.CODE_400,"用户名密码为空");
        }

        return ResultUtils.success(loginService.login(userVo));

    }
}
