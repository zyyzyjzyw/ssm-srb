package com.tedu.java.easyexcel.dto;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.tedu.java.easyexcel.listener.ExcelStudentDTOListener;
import org.junit.Test;

/**
 * @author： zyy
 * @date： 2022/12/11 10:58
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class ExcelReadTest {

    @Test
    public void simpleReadXlsx(){
        String fileName = "F:/JAVAEE/Excels/srb/simpleWrite.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 这里默认读取第一个sheet
        EasyExcel.read(fileName, ExcelStudentDTO.class, new ExcelStudentDTOListener()).sheet().doRead();
    }

    @Test
    public void simpleReadXls() {
        String fileName = "F:/JAVAEE/Excels/srb/simpleWrite.xls";
        EasyExcel.read(fileName, ExcelStudentDTO.class, new ExcelStudentDTOListener()).excelType(ExcelTypeEnum.XLS).sheet().doRead();
    }
}
