package com.sumeng.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @date: 2020/6/5 12:40
 * @author: sumeng
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerTwoApplication.class, args);
    }
}
