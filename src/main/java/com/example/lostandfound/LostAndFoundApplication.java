package com.example.lostandfound;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.lostandfound.mapper") //扫描Mapper包
public class LostAndFoundApplication {
    public static void main(String[] args) {
        SpringApplication.run(LostAndFoundApplication.class, args);
    }
}