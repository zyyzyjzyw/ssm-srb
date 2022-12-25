package com.tedu.java.controller.api;

import com.alibaba.fastjson.JSON;
import com.tedu.java.hfb.RequestHelper;
import com.tedu.java.pojo.vo.UserBindVO;
import com.tedu.java.result.R;
import com.tedu.java.service.UserBindService;
import com.tedu.java.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author： zyy
 * @date： 2022/12/20 14:18
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(tags = "会员账号绑定")
@RestController
@RequestMapping("/api/core/userBind")
@Slf4j
public class UserBindController {

    @Resource
    private UserBindService userBindService;

    @ApiOperation("账户绑定异步回调")
    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        //汇付宝向尚融宝发起的回调函数的参数
        Map<String, Object> paramMap = RequestHelper.switchMap(request.getParameterMap());
        log.info("用户账号绑定异步回调：" + JSON.toJSONString(paramMap));
        //验签
        boolean signEquals = RequestHelper.isSignEquals(paramMap);
        if(!signEquals){
            log.error("用户账号绑定异步回调签名错误：" + JSON.toJSONString(paramMap));
            return "fail";
        }
        log.info("验签成功!开始账户绑定");
        userBindService.notify(paramMap);
        return "success";
    }

    @ApiOperation("账户绑定提交数据")
    @PostMapping("/auth/bind")
    public R bind(@RequestBody UserBindVO userBindVO, HttpServletRequest request){
        // 从head中获取token，并对token进行校验，确保用户已登录，并从token中提取userId
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        // 根据userId做账户绑定,生成一个动态表单的字符串
        String formStr = userBindService.commitBindUser(userBindVO,userId);
        return R.ok().data("formStr",formStr);
    }
}
