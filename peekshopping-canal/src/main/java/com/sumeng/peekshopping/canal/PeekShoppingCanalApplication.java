package com.sumeng.peekshopping.canal;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * @date: 2020/6/13 20:04
 * @author: sumeng
 */
@SpringBootApplication
@EnableCanalClient
@EnableEurekaClient
public class PeekShoppingCanalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeekShoppingCanalApplication.class, args);
    }

}
