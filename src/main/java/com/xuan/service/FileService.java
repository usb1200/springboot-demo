package com.xuan.service;

import com.xuan.entity.Files;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    Integer insertFile( Files files);

    Files getFileByMd5(String md5);
}
