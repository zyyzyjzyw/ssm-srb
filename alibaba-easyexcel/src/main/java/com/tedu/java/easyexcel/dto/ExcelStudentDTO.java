package com.tedu.java.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author： zyy
 * @date： 2022/12/11 10:25
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
public class ExcelStudentDTO {
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("生日")
    private Date birthday;
    @ExcelProperty("工资")
    private Double salary;
}
