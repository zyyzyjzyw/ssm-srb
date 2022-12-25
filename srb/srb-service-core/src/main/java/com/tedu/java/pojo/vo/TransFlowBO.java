package com.tedu.java.pojo.vo;

import com.tedu.java.enums.TransTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author： zyy
 * @date： 2022/12/24 15:15
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransFlowBO {

    private String agentBillNo;
    private String bindCode;
    private BigDecimal amount;
    private TransTypeEnum transTypeEnum;
    private String memo;
}
