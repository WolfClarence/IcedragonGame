package com.icedragongame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * <p>
 *     project: snow_dragonGame blogSystem
 *
 *  该类名称为:
 *     <name>
 *
 *  该类作用为:
 *   <effect>
 *
 *@author gengxuelong
 *
 */
@Configuration
public class SwaggerConfig {

    /**
     * <p>
     *     project: snow_dragonGame blogSystem
     *
     *  该方法名称为:
     *     <name>
     *
     *  该方法作用为:
     *   <effect>
     *
     *   该方法设计参数描述:
     *   <description>
     *
     */
    @Bean
    public Docket docket(Environment environment){
        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev");//可以填多个值 用逗号分割或者是|分割
        //通过environment.acceptsProfiles判断是否处在自己设定的环境当中
        return new Docket(DocumentationType.SWAGGER_2)
                //配置Swagger信息=apiInfo
                .apiInfo(new ApiInfo("冰雪龙API文档",
                        "好好学习，天天向上.----智者",
                        "1.0",
                        "http://localhost:8081/hello",
                        new Contact("耿雪龙", "https://ggg1100.github.io", "3349495429@qq.com"),
                        "Apache 2.0",
                        "http://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList<>()))
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.icedragongame.controller"))
                .build();
    }
}
