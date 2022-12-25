package com.tedu.java.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author： zyy
 * @date： 2022/12/18 20:00
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
@ApiModel(description="会员搜索对象")
public class UserInfoQuery {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "1：出借人 2：借款人")
    private Integer userType;
}
