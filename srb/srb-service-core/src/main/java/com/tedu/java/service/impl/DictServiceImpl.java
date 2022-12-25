package com.tedu.java.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tedu.java.listener.ExcelDictDTOListener;
import com.tedu.java.mapper.DictMapper;
import com.tedu.java.pojo.Dict;
import com.tedu.java.pojo.dto.ExcelDictDTO;
import com.tedu.java.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author zyy
 * @since 2022-12-07
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    private DictMapper dictMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("Excel导入成功!");
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteDict() {
        //因为dict有主键策略，所以在导入时，先清空原表的数据
        dictMapper.deleteDict();
    }

    /**
     * 获取数据字典中的数据
     * @return
     */
    @Override
    public List<ExcelDictDTO> listDictData() {
        List<Dict> dictList = baseMapper.selectList(null);
        //创建ExcelDictDTO列表，将Dict列表转换为ExcelDictDTO列表
        ArrayList<ExcelDictDTO> excelDtoList = new ArrayList<>(dictList.size());
        dictList.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict,excelDictDTO);
            excelDtoList.add(excelDictDTO);
        });

        return excelDtoList;
    }

    /**
     * 根据父id查询当前列表
     * @param parentId
     * @return
     */
    @Override
    public List<Dict> listByParentId(Long parentId) {
        //首先查询redis中是否存在数据列表
        try {
            List<Dict> dictListOfRedis = (List<Dict>)redisTemplate.opsForValue().get("srb:core:dictList" + parentId);
            if(dictListOfRedis!=null){
                return dictListOfRedis;
            }
        } catch (Exception exception) {
            log.error("redis服务异常"+ ExceptionUtils.getStackTrace(exception));
            exception.printStackTrace();
        }
        //如果存在则从redis中直接返回数据列表
        //如果不存在则直接查询数据库
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",parentId);
        List<Dict> dictList = baseMapper.selectList(wrapper);
        //填充hasChildren字段
        dictList.forEach(dict -> {
            //判断当前节点是否有子节点,找到当前的dict下有没有子节点
            boolean flag = this.hasChildren(dict.getId());
            dict.setHasChildren(flag);
        });
        try {
            //将数据存入redis中
            redisTemplate.opsForValue().set("srb:core:dictList" + parentId,dictList,15, TimeUnit.MINUTES);
        } catch (Exception exception) {
            log.error("redis服务异常"+ ExceptionUtils.getStackTrace(exception));
        }
        //返回数据
        return dictList;
    }

    @Override
    public List<Dict> findByDictCode(String dictCode) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dict_code",dictCode);
        Dict dict = baseMapper.selectOne(wrapper);
        return this.listByParentId(dict.getId());
    }

    @Override
    public String getNameByParentDictCodeAndValue(String dictCode, Integer value) {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<Dict>();
        dictQueryWrapper.eq("dict_code", dictCode);
        Dict parentDict = baseMapper.selectOne(dictQueryWrapper);

        if(parentDict == null) {
            return "";
        }

        dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper
                .eq("parent_id", parentDict.getId())
                .eq("value", value);
        Dict dict = baseMapper.selectOne(dictQueryWrapper);

        if(dict == null) {
            return "";
        }

        return dict.getName();
    }

    private boolean hasChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        if(count>0){
            return true;
        }
        return false;
    }
}
