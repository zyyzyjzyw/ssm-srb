package com.tedu.java.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author： zyy
 * @date： 2022/12/25 19:13
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Configuration
public class MQConfig {

    @Bean
    public MessageConverter messageConverter(){
        //json字符串转换器
        return new Jackson2JsonMessageConverter();
    }
}