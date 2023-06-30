package com.icedragongame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//woshigeshabi
//test
@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
@EnableScheduling//开启定时器功能
public class IceDragonGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(IceDragonGameApplication.class, args);}
}
