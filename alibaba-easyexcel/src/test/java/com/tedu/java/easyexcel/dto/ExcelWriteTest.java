package com.tedu.java.easyexcel.dto;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author： zyy
 * @date： 2022/12/11 10:27
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class ExcelWriteTest {

    @Test
    public void simpleWriteTestXlsx(){
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = "F:/JAVAEE/Excels/srb/simpleWrite.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName,ExcelStudentDTO.class).sheet("模板").doWrite(data());
    }
    @Test
    public void simpleWriteTestXls(){
        // 注意 simpleWrite在数据量不大的情况下可以使用（5000以内，具体也要看实际情况），数据量大参照 重复多次写入
        // 写法1 JDK8+
        // since: 3.0.0-beta1
        String fileName = "F:/JAVAEE/Excels/srb/simpleWrite.xls";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName,ExcelStudentDTO.class).excelType(ExcelTypeEnum.XLS).sheet("模板").doWrite(data());
    }
    private List<ExcelStudentDTO> data() {
        List<ExcelStudentDTO> list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            ExcelStudentDTO data = new ExcelStudentDTO();
            data.setName("字符串" + i);
            data.setBirthday(new Date());
            data.setSalary(0.56);
            list.add(data);
        }
        return list;
    }
}
