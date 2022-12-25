package com.tedu.java.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.TransFlow;
import com.tedu.java.pojo.vo.TransFlowBO;

import java.util.List;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface TransFlowService extends IService<TransFlow> {

    List<TransFlow> selectByUserId(Long userId);
    void saveTransFlow(TransFlowBO transFlowBO);
    boolean isSaveTransFlow(String agentBillNo);
}
