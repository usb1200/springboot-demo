package com.xuan.service.Impl;

import com.xuan.entity.Files;
import com.xuan.mapper.FileMapper;
import com.xuan.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * FileName: FileServiceImpl.java
 * 类的详细说明
 *
 * @author cmx
 * @version 1.0
 * @Date 2022.08.02
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileMapper fileMapper;

    @Override
    public Integer insertFile(Files files) {

        return fileMapper.insertFile(files);
    }

    @Override
    public Files getFileByMd5(String md5) {

        return fileMapper.getFileByMd5(md5);
    }
}