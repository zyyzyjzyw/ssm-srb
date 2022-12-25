package com.tedu.java.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.tedu.java.config.OssProperties;
import com.tedu.java.service.FileService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author： zyy
 * @date： 2022/12/15 15:10
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(InputStream inputStream, String module, String fileName) {
       /* // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "yourAccessKeyId";
        String accessKeySecret = "yourAccessKeySecret";*/
        //创建oss实例
        OSS ossClient = new OSSClientBuilder().build(
                "http://"+OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET);
        //判断BUCKET_NAME是否存在,
        if(!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)){
            ossClient.createBucket(OssProperties.BUCKET_NAME);
            ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }
        //上传文件
        //文件目录结构‘"BUCKET_NAME/module/2022/12/15"
        //构建日期目录
        String timeFolder = new DateTime().toString("/yyyy/MM/dd/");
        //文件名生成
        fileName = UUID.randomUUID().toString()+fileName.substring(fileName.lastIndexOf("."));
        String key = module+timeFolder+fileName;
        ossClient.putObject(OssProperties.BUCKET_NAME,key,inputStream);
        ossClient.shutdown();
        //文件的url地址
        return "https://"+OssProperties.BUCKET_NAME+"."+OssProperties.ENDPOINT+"/"+key;
    }

    /**
     * 删除文件
     * @param url
     */
    @Override
    public void removeFile(String url) {
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(
                "http://"+OssProperties.ENDPOINT,
                OssProperties.KEY_ID,
                OssProperties.KEY_SECRET);

        //文件名（服务器上的文件路径）
        String host = "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/";
        String objectName = url.substring(host.length());

        // 删除文件。
        ossClient.deleteObject(OssProperties.BUCKET_NAME, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
