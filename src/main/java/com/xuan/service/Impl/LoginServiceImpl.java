package com.xuan.service.Impl;

import com.github.pagehelper.util.StringUtil;
import com.xuan.common.ErrorCode;
import com.xuan.entity.User;
import com.xuan.entity.UserVo;
import com.xuan.exception.ServiceException;
import com.xuan.mapper.LoginMapper;
import com.xuan.service.LoginService;
import com.xuan.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: LoginServiceImpl.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.07.23
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Override
    public UserVo login(UserVo userVo) {
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            throw new ServiceException(ErrorCode.CODE_600,"用户名或密码为空");
        }
        UserVo login = loginMapper.login(userVo.getUsername(), userVo.getPassword());

        if (login == null){
            throw new ServiceException(ErrorCode.CODE_400,"用户名或密码错误");
        }else {
            String token = TokenUtils.genToken(login.getId().toString(),login.getPassword());
            login.setToken(token);
            login.setPassword("");
            System.out.println();
            return login;
        }
    }

    @Override
    public int register(UserVo userVo) {
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)){
            throw new ServiceException(ErrorCode.CODE_600,"用户名或密码为空");
        }
        int i = loginMapper.selectName(username);
        if (i > 0){
            throw new ServiceException(ErrorCode.CODE_600,"用户名已被占用");
        }
        return loginMapper.insertUser(username,password);
    }

    @Override
    public User getUser(String username) {
        if (StringUtil.isEmpty(username)){
            throw new ServiceException(ErrorCode.CODE_400,"参数为空");
        }
        User user = loginMapper.getUser(username);
        user.setPassword("");
        return user;
    }

    @Override
    public User getById(String id) {
        return loginMapper.getById(id);
    }
}
