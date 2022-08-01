package com.xuan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuan.entity.User;
import com.xuan.mapper.UserMapper;
import com.xuan.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: UserServiceImpl.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.06.29
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    public Integer save(User user) {
        return userMapper.save(user);
    }

    @Override
    public Integer update(User user) {
        return userMapper.update(user);
    }

    @Override
    public Integer remove(Integer id) {
        return userMapper.remove(id);
    }

    @Override
    public int deleteByIds(int[] id) {
        return userMapper.deleteByIds(id);
    }

    @Override
    public int saveBatch(List<User> users) {
        return userMapper.saveBatch(users);
    }

    @Override
    public PageInfo<User> findUser(Integer pageNum, Integer pageSize, String username,String email,String address) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.findUser(username,email,address);

        return new PageInfo<>(users);
    }
}
