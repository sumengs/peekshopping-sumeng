package com.sumeng.peekshopping.goods;

import com.github.pagehelper.PageInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
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


//    @Bean
//    public PageInterceptor get() {
//        return new PageInterceptor();
//    }
}
