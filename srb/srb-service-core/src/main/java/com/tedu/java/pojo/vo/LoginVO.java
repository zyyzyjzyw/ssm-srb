package com.tedu.java.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author： zyy
 * @date： 2022/12/18 13:48
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
@ApiModel(description="登录对象")
public class LoginVO {

    @ApiModelProperty(value = "用户类型")
    private Integer userType;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;
}