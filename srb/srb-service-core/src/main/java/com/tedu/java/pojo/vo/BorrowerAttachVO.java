package com.tedu.java.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author： zyy
 * @date： 2022/12/21 10:47
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
@ApiModel(value="借款人附件资料")
public class BorrowerAttachVO {

    @ApiModelProperty(value = "图片类型（idCard1：身份证正面，idCard2：身份证反面，house：房产证，car：车）")
    private String imageType;

    @ApiModelProperty(value = "图片路径")
    private String imageUrl;
}
