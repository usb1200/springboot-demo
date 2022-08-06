package com.xuan.mapper;

import com.xuan.entity.Files;

public interface FileMapper {

    Integer insertFile(Files files);

    Files getFileByMd5(String md5);
}
