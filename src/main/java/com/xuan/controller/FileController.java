package com.xuan.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.xuan.common.ErrorCode;
import com.xuan.common.ResultUtils;
import com.xuan.entity.Files;
import com.xuan.entity.User;
import com.xuan.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 文件上传相关接口
 * @author cmx
 * @version 1.0
 * @Date 2022.08.02
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Value("${server.ip}")
    private String serverIp;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IOException{
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();

        // 定义一个文件唯一的标识码
        String fileUUID = IdUtil.fastSimpleUUID() + StrUtil.DOT + type;

        File uploadFile = new File(fileUploadPath + fileUUID);
        // 判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        File parentFile = uploadFile.getParentFile();
        if(!parentFile.exists()) {
            parentFile.mkdirs();
        }

        String url;
        // 获取文件的md5
        String md5 = SecureUtil.md5(file.getInputStream());
        // 通过文件的md5查询文件
        Files dbFiles = fileService.getFileByMd5(md5);
        if (dbFiles != null) {
            url = dbFiles.getUrl();
        } else {
            // 上传文件到磁盘
            file.transferTo(uploadFile);
            // 数据库若不存在重复文件，则不删除刚才上传的文件
            url = "http://" + serverIp + ":9090/file/" + fileUUID;
        }
        Files files = new Files();
        files.setName(originalFilename);
        files.setType(type);
        files.setSize(size/1024);  // 单位 kb
        files.setUrl(url);
        files.setMd5(md5);
        fileService.insertFile(files);

        return url;
    }

    /**
     *  文件下载接口 http://" + serverIp + ":9090/file/" + fileUUID;
     * @param fileUUID 文件uuid
     * @param response HttpServletResponse
     * @throws IOException 异常
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 根据文件的唯一标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取文件的字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }

    // 查询文件
    @RequestMapping("/page")
    public ResultUtils findFile(@RequestParam(value = "pageNum",required = false)Integer pageNum,
                                @RequestParam(value = "pageSize",required = false)Integer pageSize,
                                @RequestParam(value = "name",required = false) String name

    ){
        return ResultUtils.success(fileService.findFile(pageNum,pageSize,name));
    }

    /**
     * 根据id修改删除状态
     * @param id 文件id
     * @return 1
     */
    @PostMapping("/{id}")
    public ResultUtils remove(@PathVariable("id") Integer id){
        if (id <= 0){
            return ResultUtils.error(ErrorCode.CODE_400,"参数为空");
        }
        Integer remove = fileService.deleteById(id);
        return  ResultUtils.success(remove);
    }

    /**
     * 根据id修改删除状态
     * @param ids 文件id
     * @return 1
     */
    @PostMapping("/del/batch")
    public ResultUtils deleteByIds(@RequestBody int[] ids){
        if (ids == null){
            return ResultUtils.error(ErrorCode.CODE_400,"参数为空");
        }
        int i = fileService.deleteByIds(ids);
        return ResultUtils.success(i) ;
    }

    /**
     *  文件状态的修改
     * @param files 文件信息
     * @return 1
     */
    @PostMapping("/update")
    public ResultUtils update(@RequestBody Files files){
        if (files.getEnable() == null){
            return ResultUtils.error(ErrorCode.CODE_400,"状态值为空");
        }
        Integer update = fileService.update(files);
        return  ResultUtils.success(update);
    }
}
