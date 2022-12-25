package com.tedu.java.controller.api;


import com.tedu.java.pojo.TransFlow;
import com.tedu.java.result.R;
import com.tedu.java.service.TransFlowService;
import com.tedu.java.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 交易流水表 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
@Api(tags = "资金记录")
@RestController
@RequestMapping("/api/core/transFlow")
@Slf4j
public class TransFlowController {
    @Resource
    private TransFlowService transFlowService;

    @ApiOperation("获取列表")
    @GetMapping("/list")
    public R list(HttpServletRequest request) {
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        List<TransFlow> list = transFlowService.selectByUserId(userId);
        return R.ok().data("list", list);
    }
}

