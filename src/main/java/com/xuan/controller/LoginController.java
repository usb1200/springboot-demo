package com.xuan.controller;

import com.github.pagehelper.util.StringUtil;
import com.xuan.common.ErrorCode;
import com.xuan.common.ResultUtils;
import com.xuan.entity.User;
import com.xuan.entity.UserVo;
import com.xuan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            return ResultUtils.error(ErrorCode.CODE_400,"用户名或密码为空");
        }
        return ResultUtils.success(loginService.login(userVo));
    }

    @PostMapping("/register")
    public ResultUtils register(@RequestBody UserVo userVo){
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            return ResultUtils.error(ErrorCode.CODE_400,"用户名或密码为空");
        }
        return ResultUtils.success(loginService.register(userVo));
    }

    @GetMapping("/getUser/{username}")
    public ResultUtils getUser(@PathVariable("username") String username){
        if (StringUtil.isEmpty(username)){
            return ResultUtils.error(ErrorCode.CODE_400,"用户名为空");
        }
        return ResultUtils.success(loginService.getUser(username));
    }


}
