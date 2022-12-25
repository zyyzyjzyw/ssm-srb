package com.tedu.java.controller.admin;


import com.tedu.java.pojo.UserLoginRecord;
import com.tedu.java.result.R;
import com.tedu.java.service.UserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户登录记录表 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
@Api(tags = "会员登录日志接口")
@RestController
@RequestMapping("/admin/core/userLoginRecord")
@Slf4j
//@CrossOrigin
public class AdminUserLoginRecordController {
    @Resource
    private UserLoginRecordService userLoginRecordService;

    @ApiOperation("获取会员登录日志列表")
    @GetMapping("/listTop50/{userId}")
    public R listTop50(
            @ApiParam(value="用户id",required = true)
            @PathVariable long userId
    ){
       List<UserLoginRecord> userLoginRecords =  userLoginRecordService.listTop50(userId);
        return R.ok().data("list",userLoginRecords);
    }
}

