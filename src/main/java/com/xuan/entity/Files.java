package com.xuan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * FileName: File.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.08.02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Files {

    private Integer id;
    private String name;
    private String type;
    private Long size;
    private String url;
    private String md5;
    private Boolean isDelete;
    private Boolean enable;
}
