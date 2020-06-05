package com.sumeng.peekshopping.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/5 18:58
 * @author: sumeng
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"com.sumeng.peekshopping.goods.dao"})
public class PeekShoppingGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeekShoppingGoodApplication.class, args);
    }
}
