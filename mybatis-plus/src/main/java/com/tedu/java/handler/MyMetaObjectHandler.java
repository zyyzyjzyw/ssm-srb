package com.tedu.java.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author： zyy
 * @date： 2022/12/3 19:31
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //实现填充业务逻辑
        this.strictInsertFill(metaObject,"createTime", LocalDateTime.class,LocalDateTime.now());

        Object age = this.getFieldValByName("age", metaObject);
        if(age==null){
            this.strictInsertFill(metaObject,"age", Integer.class,3);
        }
        if(metaObject.hasSetter("author")){
            this.strictInsertFill(metaObject,"author",String.class,"张三");
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime", LocalDateTime.class,LocalDateTime.now());
    }
}
