package com.tedu.java.service;

import com.tedu.java.pojo.Dict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tedu.java.pojo.dto.ExcelDictDTO;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);

    void deleteDict();

    List<ExcelDictDTO> listDictData();

    List<Dict> listByParentId(Long parentId);

    List<Dict> findByDictCode(String dictCode);

    String getNameByParentDictCodeAndValue(String dictCode, Integer value);
}
