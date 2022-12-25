package com.tedu.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.java.pojo.BorrowInfo;

import java.util.List;

/**
 * <p>
 * 借款信息表 Mapper 接口
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {
    List<BorrowInfo> selectBorrowInfoList();
}
