package com.xiexin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiexin.dao")
public class CangkuApplication {

    public static void main(String[] args) {
        //静态调用springApplication应用，参数为本项目的启动类
        System.out.println("springboot项目启动了");
        SpringApplication.run(CangkuApplication.class, args);
    }

}
