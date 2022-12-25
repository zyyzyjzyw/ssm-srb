package com.tedu.java.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author： zyy
 * @date： 2022/12/4 8:47
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;
}
