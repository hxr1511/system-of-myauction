package com.gyk.myauction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gyk.myauction.mapper")
public class MyauctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyauctionApplication.class, args);
    }

}
