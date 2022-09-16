package com.xuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * FileName: Role.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.09.16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    private Integer id;

    private String name;

    private String description;

    private String flag;

}
