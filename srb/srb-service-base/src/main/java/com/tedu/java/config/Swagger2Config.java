package com.tedu.java.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author： zyy
 * @date： 2022/12/8 20:48
 * @description： TODO
 * @version: 1.0
 * @描述：
 **/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket adminApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("adminApi").apiInfo(adminApiInfo())
                .select().paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("webApi").apiInfo(webApiInfo())
                .select().paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    private ApiInfo adminApiInfo(){
       return new ApiInfoBuilder().title("尚融宝后台管理API").description("本文档描述了尚融宝后台管理系统的各个模块的调用接口测试"
        ).version("1").contact(new Contact("zyy","www.zyyzyjzyw.com","471321885@qq.com")).build();
    }
    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder().title("尚融宝前台管理API").description("本文档描述了尚融宝前台网页端的各个模块的调用接口测试"
        ).version("1").contact(new Contact("zyy","www.zyyzyjzyw.com","471321885@qq.com")).build();
    }
}
