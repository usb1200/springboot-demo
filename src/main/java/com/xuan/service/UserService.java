package com.xuan.service;

import com.github.pagehelper.PageInfo;
import com.xuan.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * FileName: UserService.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.06.29
 */

@Service
public interface UserService {

    List<User> findAll();

    PageInfo<User> findUser(Integer pageNum, Integer pageSize,String username,@Param("email")String email,@Param("address")String address);

    Integer save(User user);

    Integer update(User user);

    Integer remove(@Param("id") Integer id);

    int deleteByIds(int[] id);

    int saveBatch(List<User> users);

}
