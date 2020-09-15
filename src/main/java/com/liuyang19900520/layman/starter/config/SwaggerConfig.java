package com.liuyang19900520.layman.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2020/09/13
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        //设置全局响应状态码
        List<Response> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseBuilder().code("200").description("请求成功").build());
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                //报名不能设置通配符*
                .apis(RequestHandlerSelectors.basePackage("com.liuyang19900520.layman.starter.module"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("layman-starter接口文档")
                .description("layman-starter接口文档，测试使用")
                .version("0.0.1")
                .contact(new Contact("Max Liu", "https://www.liuyang1990520.com", "liuyang19900520@hotmail.com"))
                .build();
    }
}
