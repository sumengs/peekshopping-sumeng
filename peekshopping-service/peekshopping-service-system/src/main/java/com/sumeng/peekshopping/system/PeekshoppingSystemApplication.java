package com.sumeng.peekshopping.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/9 19:42
 * @author: sumeng
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(value = "com.sumeng.peekshopping.system.dao")
public class PeekshoppingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeekshoppingSystemApplication.class, args);
    }
}
