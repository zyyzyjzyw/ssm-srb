package com.tedu.java.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.tedu.java.pojo.Dict;
import com.tedu.java.pojo.dto.ExcelDictDTO;
import com.tedu.java.result.R;
import com.tedu.java.result.ResponseEnum;
import com.tedu.java.result.exception.BusinessException;
import com.tedu.java.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
@Api(tags = "数据字典管理")
@RestController
@RequestMapping("/admin/core/dict")
@Slf4j
//@CrossOrigin
public class AdminDictController {
    @Resource
    private DictService dictService;

    /**
     * 延迟加载数据
     */
    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{parentId}")
    public R listByParentId(@ApiParam(value = "上级节点id",required = true)
                            @PathVariable Long parentId){
    List<Dict> dictList = dictService.listByParentId(parentId);
    return R.ok().data("list",dictList);

    }

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ExcelDictDTO}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @ApiOperation("Excel数据的导出")
    @GetMapping("/export")
    public void download(HttpServletResponse response) throws IOException {
        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listDictData());

        } catch (IOException e) {
            //EXPORT_DATA_ERROR(104, "数据导出失败"),
            throw  new BusinessException(ResponseEnum.EXPORT_DATA_ERROR, e);
        }
    }

    @PostMapping("/import")
    @ApiOperation("Excel的数据导入")
    public R batchImport(
            @ApiParam(value = "Excel数据字典文件",required = true)
            @RequestParam("file")MultipartFile file){
        try {
            //先查询数据字典的数据是否为空，如果不为空，现将数据删除
            List<Dict> dictList = dictService.list();
            if(dictList.size()>0 || dictList!=null){
                dictService.deleteDict();
            }
            InputStream inputStream = file.getInputStream();
            dictService.importData(inputStream);
            return R.ok().message("数据字典导入成功");
        } catch (Exception e) {
            //UPLOAD_ERROR(-103, "文件上传错误"),
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }
    }
}

