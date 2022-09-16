package com.xuan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuan.common.ErrorCode;
import com.xuan.entity.Role;
import com.xuan.entity.User;
import com.xuan.exception.ServiceException;
import com.xuan.mapper.RoleMapper;
import com.xuan.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: ImplServiceImpl.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.09.16
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public PageInfo<Role> findRole(Integer pageNum, Integer pageSize, String name) {

        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles = roleMapper.findRole(name);

        return new PageInfo<>(roles);
    }

    @Override
    public Integer saveRole(Role role) {
        return roleMapper.saveRole(role);
    }

    @Override
    public Integer updateRole(Role role) {
        if (role == null){
            throw new ServiceException(ErrorCode.CODE_600,"用户数据为空");
        }
        return roleMapper.updateRole(role);
    }

    @Override
    public Integer removeRole(Integer id) {
        if ( id == 0){
            throw new ServiceException(ErrorCode.CODE_600,"数据id为空");
        }
        return roleMapper.removeRole(id);
    }

    @Override
    public int deleteByIds(int[] id) {
        if ( id.length <= 0){
            throw new ServiceException(ErrorCode.CODE_600,"数据id为空");
        }
        return roleMapper.deleteByIds(id);
    }

    @Override
    public int saveBatch(List<Role> roles) {
        return roleMapper.saveBatch(roles);
    }
}
