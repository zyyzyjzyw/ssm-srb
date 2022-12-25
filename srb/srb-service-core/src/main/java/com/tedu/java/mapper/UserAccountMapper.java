package com.tedu.java.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.java.pojo.UserAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户 Mapper 接口
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    void updateAccount(
            @Param("bindCode")String bindCode,
            @Param("amount")BigDecimal amount,
            @Param("freezeAmount")BigDecimal freezeAmount);
}
