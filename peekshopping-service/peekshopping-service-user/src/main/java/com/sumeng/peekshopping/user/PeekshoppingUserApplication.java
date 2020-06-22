package com.sumeng.peekshopping.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/20 19:37
 * @author: sumeng
 */
@SpringBootApplication
@MapperScan(value = "com.sumeng.peekshopping.user.dao")
@EnableEurekaClient
public class PeekshoppingUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeekshoppingUserApplication.class, args);
    }
}
