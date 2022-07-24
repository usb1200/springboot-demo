package com.xuan.service;

import com.xuan.entity.User;
import com.xuan.entity.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {

    User login(@Param("userVo") UserVo userVo);
}
