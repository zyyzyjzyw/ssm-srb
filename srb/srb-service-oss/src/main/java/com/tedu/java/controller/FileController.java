package com.tedu.java.controller;

import com.tedu.java.result.R;
import com.tedu.java.result.ResponseEnum;
import com.tedu.java.result.exception.BusinessException;
import com.tedu.java.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author： zyy
 * @date： 2022/12/15 16:09
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(tags = "阿里云文件管理")
//@CrossOrigin //跨域
@RestController
@RequestMapping("/api/oss/file")
public class FileController {
    @Resource
    private FileService fileService;
    @ApiOperation("删除OSS文件")
    @DeleteMapping("/remove")
    public R remove(
            @ApiParam(value = "要删除的文件路径", required = true)
            @RequestParam("url") String url) {
        fileService.removeFile(url);
        return R.ok().message("删除成功");
    }

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(value = "文件",required = true)
            @RequestParam("file") MultipartFile file,
            @ApiParam(value = "模块",required = true)
            @RequestParam("module") String module
            ){
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            //图片上传后的url地址
            String url = fileService.upload(inputStream, module, originalFilename);
            return R.ok().message("文件上传成功").data("url",url);
            //https://srb-upload.oss-cn-hangzhou.aliyuncs.com/test2022/12/155d85018b-e5d8-4144-9cf3-948fe4202271.csv
            //https://srb-upload.http://oss-cn-hangzhou.aliyuncs.com/test2022/12/155d85018b-e5d8-4144-9cf3-948fe4202271.csv

        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }
    }
}
