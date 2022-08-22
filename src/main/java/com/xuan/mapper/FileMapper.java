package com.xuan.mapper;

import com.xuan.entity.Files;
import com.xuan.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper {

    Integer insertFile(Files files);

    Files getFileByMd5(String md5);

    List<Files> findFile(String name);

    // 修改删除状态
    int deleteById(@Param("id") Integer id);

    // 批量删除
    int deleteByIds(int[] id);

    Integer update(Files files);
}
