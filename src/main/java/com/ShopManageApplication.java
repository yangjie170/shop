package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.shopmanage.mapper")
public class ShopManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopManageApplication.class, args);
    }
}
