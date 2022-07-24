package com.xuan.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.github.pagehelper.PageInfo;
import com.xuan.common.ErrorCode;
import com.xuan.common.ResultUtils;
import com.xuan.entity.User;
import com.xuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
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

    /**
     * 查询所有用户
     * @return 所有用户信息
     */
    @RequestMapping("/")
    public List<User> index(){
        return userService.findAll();
    }

    /**
     *  添加用户
     * @param user user
     * @return 1
     */
    @RequestMapping("save")
    public ResultUtils save(@RequestBody User user){
        if (user == null){
            return ResultUtils.error(ErrorCode.CODE_400,"参数为空");
        }
        Integer save = userService.save(user);
        return ResultUtils.success(save);
    }

    /**
     *  用户信息修改
     * @param user 用户信息
     * @return 1
     */
    @RequestMapping("update")
    public ResultUtils update(@RequestBody User user){
        if (user == null){
            return ResultUtils.error(ErrorCode.CODE_400,"参数为空");
        }
        Integer update = userService.update(user);
        return  ResultUtils.success(update);
    }

    /**
     * 根据id删除User
     * @param id 用户id
     * @return 1
     */
    @RequestMapping("remove/{id}")
    public ResultUtils remove(@PathVariable("id") Integer id){
        if (id <= 0){
            return ResultUtils.error(ErrorCode.CODE_400,"参数为空");
        }
        Integer remove = userService.remove(id);
        return  ResultUtils.success(remove);
    }

    /**
     *  根据条件模糊分页查询
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param username 姓名
     * @param email 邮箱
     * @param address 地址
     * @return User
     */
    @RequestMapping("findPageUser")
    public ResultUtils findUser(@RequestParam(value = "pageNum",required = false)Integer pageNum,
                                   @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                   @RequestParam(value = "username",required = false) String username,
                                   @RequestParam(value = "email",required = false) String email,
                                   @RequestParam(value = "address",required = false) String address
                                ){

        return ResultUtils.success(userService.findUser(pageNum,pageSize,username,email,address));
    }

    /**
     * 批量删除
     * @param ids 用户id
     * @return 1
     */
    @RequestMapping("deleteByIds/{ids}")
    public ResultUtils deleteByIds(@PathVariable("ids") int[] ids){
        if (ids == null){
            return ResultUtils.error(ErrorCode.CODE_400,"参数为空");
        }
        int i = userService.deleteByIds(ids);
        return ResultUtils.success(i) ;
    }

    /**
     * 导出接口
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        // 获取所有用户信息
        List<User> list = userService.findAll();
        // 通过工具类创建writer
//        ExcelWriter writer = ExcelUtil.getWriter("d:/writeMapTest.xlsx");
        // 在内存操作，写到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        // 自定义标题别名
//        writer.addHeaderAlias("username","用户名");
//        writer.addHeaderAlias("password","密码");
//        writer.addHeaderAlias("nickname","昵称");
//        writer.addHeaderAlias("email","邮箱");
//        writer.addHeaderAlias("phone","电话");
//        writer.addHeaderAlias("address","地址");
//        writer.addHeaderAlias("createTime","创建时间");
//        writer.addHeaderAlias("avatarUrl","头像");

        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        // 关闭writer，释放内存
        writer.close();
    }

    /**
     * excel导入
     */
    @RequestMapping("/import")
    public ResultUtils imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<User> list = reader.readAll(User.class);
//        System.out.println(list);

       // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<User> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            User user = new User();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setNickname(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            user.setVia(row.get(6).toString());
            users.add(user);
//            userService.save(user);
        }
        userService.saveBatch(users);

        return ResultUtils.success(true);
    }
}
