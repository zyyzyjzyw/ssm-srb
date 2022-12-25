package com.tedu.java;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.*;

/**
 * @author： zyy
 * @date： 2022/12/7 21:50
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
public class CodeGenerator {
    @Test
    public void genCode() {

        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("zyy");
        gc.setOpen(false); //生成后是否打开资源管理器
        gc.setServiceName("%sService");	//去掉Service接口的首字母I
        gc.setIdType(IdType.AUTO); //主键策略
        gc.setSwagger2(true);//开启Swagger2模式
        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/srb_core?serverTimezone=GMT%2B8&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.tedu.java");
        pc.setEntity("pojo"); //此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略

        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的命名策略
        strategy.setEntityLombokModel(true); // lombok
        strategy.setLogicDeleteFieldName("is_deleted");//逻辑删除字段名
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);//去掉布尔值的is_前缀（确保tinyint(1)）
        strategy.setRestControllerStyle(true); //restful api风格控制器
        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }


    @Test
    public void test(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",1);
        map1.put("name","zyy");
        map1.put("age",22);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("id",2);
        map2.put("name","zyy");
        map2.put("age",23);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("id",3);
        map3.put("name","zyy");
        map3.put("age",24);
        Map<String,Object> map4 = new HashMap<>();
        map4.put("id",4);
        map4.put("name","zyj");
        map4.put("age",24);
        Map<String,Object> map5 = new HashMap<>();
        map5.put("id",5);
        map5.put("name","zyj");
        map5.put("age",25);
        Map<String,Object> map6 = new HashMap<>();
        map6.put("id",5);
        map6.put("name","zyw");
        map6.put("age",25);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        list.add(map4);
        list.add(map5);
        list.add(map6);
        System.out.println(list);
        List<Map<String, Object>> listMap = removeRepeatMapByKey(list,"id");
        System.out.println(listMap);
    }

    private List<Map<String, Object>> removeRepeatMapByKey(List<Map<String, Object>> list, String name) {
        List<Map<String, Object>> listMap = new ArrayList<>();
        Map<String, Map> msp = new HashMap<>();
        for(int i = list.size()-1 ; i>=0; i--){
            Map map = list.get(i);
            int id = (int) map.get(name);
            map.remove(name);
            msp.put(String.valueOf(id), map);
        }
        Set<String> mspKey = msp.keySet();

        for(String key: mspKey){

            Map newMap = msp.get(key);

            newMap.put(name, key);

            listMap.add(newMap);

        }
        return listMap;
    }
}
