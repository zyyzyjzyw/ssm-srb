package com.tedu.java.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author： zyy
 * @date： 2022/12/20 10:19
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Configuration
public class OpenFeignConfig {

    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}