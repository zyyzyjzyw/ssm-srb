package com.tedu.java.mapper;

import com.tedu.java.pojo.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tedu.java.pojo.dto.ExcelDictDTO;

import java.util.ArrayList;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(ArrayList<ExcelDictDTO> list);

    void deleteDict();
}
