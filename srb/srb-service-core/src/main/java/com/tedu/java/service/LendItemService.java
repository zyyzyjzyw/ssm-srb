package com.tedu.java.service;

import com.tedu.java.pojo.LendItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.vo.InvestVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 服务类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface LendItemService extends IService<LendItem> {

    String commitInvest(InvestVO investVO);

    void notify(Map<String, Object> paramMap);

    List<LendItem> selectByLendId(Long lendId, int i);

    List<LendItem> selectByLendId(Long lendId);
}
