package com.sumeng.peekshopping.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @date: 2020/6/16 11:10
 * @author: sumeng
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.sumeng.peekshopping.goods.feign")
public class PeekShoppingSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(PeekShoppingSearchApplication.class, args);
    }
}
