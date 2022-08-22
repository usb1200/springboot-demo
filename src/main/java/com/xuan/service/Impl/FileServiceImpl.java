package com.xuan.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xuan.common.ErrorCode;
import com.xuan.entity.Files;
import com.xuan.entity.User;
import com.xuan.exception.ServiceException;
import com.xuan.mapper.FileMapper;
import com.xuan.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        if (StringUtil.isEmpty(md5)){
            throw new ServiceException(ErrorCode.CODE_600,"md5数据为空");
        }
        return fileMapper.getFileByMd5(md5);
    }

    @Override
    public PageInfo<Files> findFile(Integer pageNum, Integer pageSize, String name) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        List<Files> files = fileMapper.findFile(name);

        return new PageInfo<>(files);
    }

    // 修改删除状态
    @Override
    public int deleteById(Integer id) {
        if (id <= 0){
            throw new ServiceException(ErrorCode.CODE_600,"数据id为空");
        }
        return fileMapper.deleteById(id);
    }

    @Override
    public int deleteByIds(int[] id) {
        if ( id.length <= 0){
            throw new ServiceException(ErrorCode.CODE_600,"数据id为空");
        }
        return fileMapper.deleteByIds(id);
    }

    @Override
    public Integer update(Files files) {
        if (files == null){
            throw new ServiceException(ErrorCode.CODE_600,"用户数据为空");
        }
        return fileMapper.update(files);
    }
}