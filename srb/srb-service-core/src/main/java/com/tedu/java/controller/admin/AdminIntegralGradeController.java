package com.tedu.java.controller.admin;


import com.tedu.java.pojo.IntegralGrade;
import com.tedu.java.result.R;
import com.tedu.java.result.ResponseEnum;
import com.tedu.java.result.exception.Assert;
import com.tedu.java.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
@Api("积分等级管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("积分等级列表")
    @GetMapping("/list")
    public R ListAll(){
        List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list",list).message("获取积分列表成功");

    }

    @ApiOperation(value = "根据id删除积分数据记录",notes = "逻辑删除")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(value = "数据id",example = "100",required = true)
            @PathVariable("id") Long id){
        boolean result = integralGradeService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "积分等级对象",required = true)
            @RequestBody IntegralGrade integralGrade){
        BigDecimal borrowAmount = integralGrade.getBorrowAmount();
        /*if(borrowAmount.equals(BigDecimal.ZERO)){
            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        }*/
        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean result = integralGradeService.save(integralGrade);
        if(result){
            return R.ok().message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("根据id查询积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "数据id",required = true)
            @PathVariable Long id){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if(integralGrade!=null){
            return R.ok().data("record",integralGrade);
        }
        return R.error().message("数据获取失败");
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public R updateById(
            @ApiParam(value = "积分等级对象", required = true)
            @RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("修改失败");
        }
    }

}

