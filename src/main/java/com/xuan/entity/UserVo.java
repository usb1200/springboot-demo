package com.xuan.entity;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * FileName: UserVo.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.07.23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserVo {

    private String username;
    private String password;
}
