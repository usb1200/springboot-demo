package com.xuan.controller;

import com.github.pagehelper.PageInfo;
import com.xuan.common.BaseResponse;
import com.xuan.common.ErrorCode;
import com.xuan.common.ResultUtils;
import com.xuan.entity.User;
import com.xuan.exception.BusinessException;
import com.xuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FileName: User.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.06.29
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public List<User> index(){
        return userService.findAll();
    }

    @RequestMapping("save")
    public BaseResponse<Integer> save(@RequestBody User user){
        if (user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer save = userService.save(user);
        return ResultUtils.success(save);
    }

    @RequestMapping("update")
    public BaseResponse<Integer> update(@RequestBody User user){
        if (user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Integer update = userService.update(user);
        return  ResultUtils.success(update);
    }

    @RequestMapping("remove/{id}")
    public Integer remove(@PathVariable("id") Integer id){
        if (id <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return  userService.remove(id);
    }

    @RequestMapping("findPageUser")
    public PageInfo<User> findUser(@RequestParam(value = "pageNum",required = false)Integer pageNum,
                                   @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                   @RequestParam(value = "username",required = false) String username,
                                   @RequestParam(value = "email",required = false) String email,
                                   @RequestParam(value = "address",required = false) String address
                                ){

        return userService.findUser(pageNum,pageSize,username,email,address);
    }

    @RequestMapping("deleteByIds/{ids}")
    public int deleteByIds(@PathVariable("ids") int[] ids){
        if (ids == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return userService.deleteByIds(ids);
    }
}
