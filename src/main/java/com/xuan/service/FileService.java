package com.xuan.service;

import com.github.pagehelper.PageInfo;
import com.xuan.entity.Files;
import com.xuan.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface FileService {

    Integer insertFile( Files files);

    Files getFileByMd5(String md5);

    PageInfo<Files> findFile(Integer pageNum, Integer pageSize, String name);

    // 修改删除状态
    int deleteById(Integer id);

    int deleteByIds(int[] id);

    Integer update(Files files);
}
