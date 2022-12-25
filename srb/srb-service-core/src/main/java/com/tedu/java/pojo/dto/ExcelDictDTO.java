package com.tedu.java.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author： zyy
 * @date： 2022/12/11 11:19
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
public class ExcelDictDTO {
    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("上级id")
    private Long parentId;

    @ExcelProperty("名称")
    private String name;

    @ExcelProperty("值")
    private Integer value;

    @ExcelProperty("编码")
    private String dictCode;
}
