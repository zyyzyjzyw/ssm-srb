package com.tedu.java.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author： zyy
 * @date： 2022/12/25 11:52
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
@ApiModel(description = "投标信息")
public class InvestVO {

    private Long lendId;

    //投标金额
    private String investAmount;

    //用户id
    private Long investUserId;

    //用户姓名
    private String investName;
}
