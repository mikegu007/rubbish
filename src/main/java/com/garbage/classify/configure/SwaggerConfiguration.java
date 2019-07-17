package com.garbage.classify.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于Swagger生成API文档
 *
 * @author XuQing
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("localhost:8202")
    private String swaggerHost;



    @Bean
    public Docket createRestApi() {
        //设置请求头信息
//        ParameterBuilder xyx_jwt = new ParameterBuilder();
//        ParameterBuilder api_jwt = new ParameterBuilder();
//        ParameterBuilder smcv_user_jwt = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        pars.add(xyx_jwt.build());
//        pars.add(api_jwt.build());
//        pars.add(smcv_user_jwt.build());

        return new Docket(DocumentationType.SWAGGER_2)
//                .host(swaggerHost)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.garbage.classify.controller"))
                .build()
                //.globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("[garbageService]")
                .description("垃圾分类REST接口")
                .version("0.0.1-SNAPSHOT")
                .build();
    }
}