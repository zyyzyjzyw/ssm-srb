package com.tedu.java.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tedu.java.pojo.UserLoginRecord;
import com.tedu.java.mapper.UserLoginRecordMapper;
import com.tedu.java.service.UserLoginRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户登录记录表 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
@Service
public class UserLoginRecordServiceImpl extends ServiceImpl<UserLoginRecordMapper, UserLoginRecord> implements UserLoginRecordService {

    @Override
    public List<UserLoginRecord> listTop50(long userId) {
        QueryWrapper<UserLoginRecord> wrapper =  new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        wrapper.orderByDesc("id").last("limit 50");

        return baseMapper.selectList(wrapper);
    }
}
