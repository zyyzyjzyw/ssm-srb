package com.tedu.java.controller.admin;

import com.tedu.java.pojo.LendItem;
import com.tedu.java.result.R;
import com.tedu.java.service.LendItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： zyy
 * @date： 2022/12/25 17:09
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(tags = "标的的投资")
@RestController
@RequestMapping("/admin/core/lendItem")
@Slf4j
public class AdminLendItemController {
    @Resource
    private LendItemService lendItemService;

    @ApiOperation("获取列表")
    @GetMapping("/list/{lendId}")
    public R list(
            @ApiParam(value = "标的id", required = true)
            @PathVariable Long lendId) {
        List<LendItem> list = lendItemService.selectByLendId(lendId);
        return R.ok().data("list", list);
    }
}
