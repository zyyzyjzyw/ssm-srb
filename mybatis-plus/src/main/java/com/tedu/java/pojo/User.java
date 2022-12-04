package com.tedu.java.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author： zyy
 * @date： 2022/12/3 11:00
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@TableName(value = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(type= IdType.ASSIGN_ID)//雪花算法默认类型
    private Long id;

    private String name;

    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill=FieldFill.UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    @TableField(value = "is_deleted")
    private Integer deleted;
}
