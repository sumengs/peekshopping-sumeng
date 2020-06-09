package com.sumeng.peekshopping.system.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * @date: 2020/6/9 9:19
 * @author: sumeng
 */
@Component
public class IpFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取客户端的访问ip
        System.out.println("经过了第一个过滤器");
        ServerHttpRequest request = exchange.getRequest();
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        System.out.println("ip：" + Objects.requireNonNull(remoteAddress).getHostName());

        //放行
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 2;
    }
}
