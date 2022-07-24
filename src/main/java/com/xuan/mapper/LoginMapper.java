package com.xuan.mapper;

import com.xuan.entity.User;
import com.xuan.entity.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * FileName: LoginMapper.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.07.23
 */

public interface LoginMapper {

    User login(@Param("username") String  username, @Param("password") String password);
}
