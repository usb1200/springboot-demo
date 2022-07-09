package com.xuan.mapper;

import com.xuan.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: UserMapper.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.06.29
 */

public interface UserMapper {

    List<User> findAll();

    //
    List<User> findUser(@Param("username")String username,@Param("email")String email,@Param("address")String address);

    Integer save(User user);

    Integer update(User user);

    Integer remove(@Param("id") Integer id);

    int deleteByIds(int[] id);

}
