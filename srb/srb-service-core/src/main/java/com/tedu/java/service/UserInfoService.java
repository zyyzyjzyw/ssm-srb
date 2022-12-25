package com.tedu.java.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tedu.java.pojo.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.query.UserInfoQuery;
import com.tedu.java.pojo.vo.LoginVO;
import com.tedu.java.pojo.vo.RegisterVO;
import com.tedu.java.pojo.vo.UserIndexVO;
import com.tedu.java.pojo.vo.UserInfoVO;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);


    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);
    void lock(Long id, Integer status);

    boolean checkMobile(String mobile);

    UserIndexVO getIndexUserInfo(Long userId);

    String getMobileByBindCode(String bindCode);
}
