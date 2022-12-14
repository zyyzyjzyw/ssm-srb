package com.tedu.java.controller.api;

import com.tedu.java.pojo.Dict;
import com.tedu.java.result.R;
import com.tedu.java.service.DictService;
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
 * @date： 2022/12/21 8:50
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Api(tags = "数据字典")
@RestController
@RequestMapping("/api/core/dict")
@Slf4j
public class DictController {
    @Resource
    private DictService dictService;

    @ApiOperation("根据dictCode获取下级节点")
    @GetMapping("/findByDictCode/{dictCode}")
    public R findByDictCode(
            @ApiParam(value = "节点编码", required = true)
            @PathVariable String dictCode
    ){
        List<Dict> list = dictService.findByDictCode(dictCode);
        return R.ok().data("dictList",list);
    }

}
