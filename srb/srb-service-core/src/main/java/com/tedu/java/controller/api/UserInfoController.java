package com.tedu.java.controller.api;


import com.tedu.java.pojo.vo.LoginVO;
import com.tedu.java.pojo.vo.RegisterVO;
import com.tedu.java.pojo.vo.UserIndexVO;
import com.tedu.java.pojo.vo.UserInfoVO;
import com.tedu.java.result.R;
import com.tedu.java.result.ResponseEnum;
import com.tedu.java.result.exception.Assert;
import com.tedu.java.service.UserInfoService;
import com.tedu.java.util.RegexValidateUtils;
import com.tedu.java.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
@Api(tags = "会员接口")
@RestController
@RequestMapping("/api/core/userInfo")
@Slf4j
//@CrossOrigin
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("获取个人空间用户信息")
    @GetMapping("/auth/getIndexUserInfo")
    public R getIndexUserInfo(HttpServletRequest request) {
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        UserIndexVO userIndexVO = userInfoService.getIndexUserInfo(userId);
        return R.ok().data("userIndexVO", userIndexVO);
    }

    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@PathVariable String mobile){
        boolean flag = userInfoService.checkMobile(mobile);
        return flag;
    }

    @ApiOperation("校验令牌")
    @GetMapping("/checkToken")
    public R checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        boolean flag = JwtUtils.checkToken(token);
        if(flag){
            return R.ok();
        }
        return R.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
    }

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO, HttpServletRequest request){
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        //MOBILE_NULL_ERROR(-202, "手机号不能为空"),
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //PASSWORD_NULL_ERROR(-204, "密码不能为空"),
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
        String ip = request.getRemoteAddr();
        UserInfoVO userInfoVO = (UserInfoVO) userInfoService.login(loginVO,ip);
        return R.ok().data("userInfo",userInfoVO);
    }

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO){
        String mobile = registerVO.getMobile();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();

        //MOBILE_NULL_ERROR(-202, "手机号不能为空"),
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //MOBILE_ERROR(-203, "手机号不正确"),
        Assert.isTrue(RegexValidateUtils.isMobileNO(mobile), ResponseEnum.MOBILE_ERROR);
        //PASSWORD_NULL_ERROR(-204, "密码不能为空"),
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
        //CODE_NULL_ERROR(-205, "验证码不能为空"),
        Assert.notEmpty(code, ResponseEnum.CODE_NULL_ERROR);
        // 校验验证码是否正确
        String codeGen = (String)redisTemplate.opsForValue().get("srb:sms:code:"+mobile);
        Assert.equals(code,codeGen, ResponseEnum.CODE_ERROR);
        // 注册
        userInfoService.register(registerVO);
        return R.ok().message("注册成功");
    }
}

