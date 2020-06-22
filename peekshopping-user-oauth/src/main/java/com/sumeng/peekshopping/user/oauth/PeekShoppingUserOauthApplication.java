package com.sumeng.peekshopping.user.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @date: 2020/6/18 20:10
 * @author: sumeng
 */

@SpringBootApplication
@MapperScan(value = "com.sumeng.peekshopping.user.oauth.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.sumeng.peekshopping.user.feign"})
public class PeekShoppingUserOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeekShoppingUserOauthApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
