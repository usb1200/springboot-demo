package com.xuan.mapper;

import com.xuan.entity.Role;
import com.xuan.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * FileName: RoleMapper.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.09.16
 */

public interface RoleMapper {

    List<Role> findAll();
    //
    List<Role> findRole(@Param("name")String name);

    Integer saveRole(Role role);

    Integer updateRole(Role role);

    Integer removeRole(@Param("id") Integer id);
    // 批量删除
    int deleteByIds(int[] id);
    // 批量插入
    int saveBatch(List<Role> roles);
}
