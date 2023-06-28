package com.icedragongame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//woshigeshabi
@SpringBootApplication
@ServletComponentScan
public class IceDragonGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(IceDragonGameApplication.class, args);}
}
