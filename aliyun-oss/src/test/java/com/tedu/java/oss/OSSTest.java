package com.tedu.java.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.CannedAccessControlList;
import org.junit.Test;

/**
 * @author： zyy
 * @date： 2022/12/15 11:33
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class OSSTest {
    // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
    String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    String accessKeyId = "LTAI5tPvkuCHpc9AeAHsMbkH";
    String accessKeySecret = "SfUbDsiQWa6KHLLwi3q8YIuAjYeRXC";
    String bucketName = "srb-upload";

    @Test
    public void testCreateBucket() {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //创建存储空间
        ossClient.createBucket(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }
    @Test
    public void testGetBucket(){
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 获取存储空间的访问权限
        AccessControlList acl = ossClient.getBucketAcl(bucketName);
        System.out.println(acl.toString());
        ossClient.shutdown();
    }

    @Test
    public void testSetBucket(){
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        //关闭ossClient
        ossClient.shutdown();
    }

    @Test
    public void testExistBucket(){
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean isExist = ossClient.doesBucketExist(bucketName);
        System.out.println(isExist);
        //关闭ossClient
        ossClient.shutdown();
    }
}
