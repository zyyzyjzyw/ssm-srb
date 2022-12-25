package com.tedu.java.service;

import com.tedu.java.pojo.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.vo.UserBindVO;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface UserBindService extends IService<UserBind> {

    String commitBindUser(UserBindVO userBindVO, Long userId);

    void notify(Map<String, Object> paramMap);

    String getBindCodeByUserId(Long investUserId);
}
