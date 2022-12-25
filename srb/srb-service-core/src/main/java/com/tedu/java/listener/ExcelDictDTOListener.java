package com.tedu.java.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.tedu.java.mapper.DictMapper;
import com.tedu.java.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author： zyy
 * @date： 2022/12/11 11:20
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Slf4j
@NoArgsConstructor
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {
    private DictMapper dictMapper;
    //数据列表
    ArrayList<ExcelDictDTO> list = new ArrayList<>();
    //每隔5条记录存储一次数据
    static final int BATCH_COUNT = 5;
    public ExcelDictDTOListener(DictMapper dictMapper){
        this.dictMapper=dictMapper;
    }
    

    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条记录:{}",excelDictDTO);
        //将数据存入数据列表
        list.add(excelDictDTO);
        if(list.size()>=5){
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        //当最后剩余的数据不足BATCH_COUNT，最后一次性存储剩余数据
        saveData();
        log.info("所有数据解析完成!");
    }
    private void saveData() {
        log.info("{} 条数据被存储到数据库......",list.size());
        //调用mapper层的save方法:save list对象
        dictMapper.insertBatch(list);
        log.info("{} 条记录存储数据库成功",list.size());
    }
}
