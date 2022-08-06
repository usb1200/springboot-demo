package com.xuan.service;

import com.xuan.entity.User;
import com.xuan.entity.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    UserVo login(UserVo userVo);

    int register(UserVo userVo);

    User getUser(@Param("username") String username);

    User getById(@Param("id") String id);
}
