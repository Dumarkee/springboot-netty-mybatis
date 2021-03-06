package com.xinchao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xinchao.mapper")
public class GycApplication {

    public static void main(String[] args) {
        SpringApplication.run(GycApplication.class, args);
    }
}
