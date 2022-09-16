package com.xuan.service;

import com.github.pagehelper.PageInfo;
import com.xuan.entity.Role;
import com.xuan.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAll();

    PageInfo<Role> findRole(Integer pageNum, Integer pageSize, String name);

    Integer saveRole(Role role);

    Integer updateRole(Role role);

    Integer removeRole(@Param("id") Integer id);

    int deleteByIds(int[] id);

    int saveBatch(List<Role> roles);
}
