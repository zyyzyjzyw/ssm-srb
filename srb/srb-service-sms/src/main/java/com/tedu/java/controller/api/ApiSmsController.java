package com.tedu.java.controller.api;

import com.tedu.java.client.CoreUserInfoClient;
import com.tedu.java.result.R;
import com.tedu.java.result.ResponseEnum;
import com.tedu.java.result.exception.Assert;
import com.tedu.java.service.SmsService;
import com.tedu.java.util.RandomUtils;
import com.tedu.java.util.RegexValidateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author： zyy
 * @date： 2022/12/15 10:26
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@RestController
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
//@CrossOrigin //跨域
@Slf4j
public class ApiSmsController {
    @Resource
    private SmsService smsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    /**
     * 获取验证码
     * @param mobile
     * @return
     */
    @ApiOperation(("获取验证码"))
    @GetMapping("/send/{mobile}")
    public R send(@ApiParam(value = "手机号",required = true)
                  @PathVariable String mobile){
        //校验手机号码不能为空
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //校验手机号码的合法性
        Assert.isTrue(RegexValidateUtils.isMobileNO(mobile),ResponseEnum.MOBILE_ERROR);
        //判断手机号是否被注册
        boolean result = coreUserInfoClient.checkMobile(mobile);
        System.out.println(result);
        Assert.isTrue(result==false,ResponseEnum.MOBILE_EXIST_ERROR);

        HashMap<String, Object> map = new HashMap<>();
        String code = RandomUtils.getSixBitRandom();
        map.put("code", code);
        //smsService.send(mobile,SmsProperties.TEMPLATE_CODE,map);
        //将验证码存入redis
        redisTemplate.opsForValue().set("srb:sms:code:"+mobile,code,5, TimeUnit.MINUTES);
      return R.ok().message("短信发送成功");
    }
}
