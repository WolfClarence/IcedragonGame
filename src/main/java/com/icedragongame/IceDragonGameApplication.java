package com.icedragongame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//woshigeshabi
//test
@SpringBootApplication
@ServletComponentScan
@EnableSwagger2
@EnableCaching
public class IceDragonGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(IceDragonGameApplication.class, args);}
}
