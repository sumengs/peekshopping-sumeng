package com.sumeng.peekshopping.getway.web.filter;

import com.sumeng.peekshopping.getway.web.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @date: 2020/6/22 10:07
 * @author: sumeng
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {


    @Autowired
    private AuthService authService;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        //1.判断当前请求是否为登陆请求，如果是则直接放行
        String path = request.getURI().getPath();
        if ("/api/oauth/login".equals(path) || UrlFilter.hasAuthorize(path)) {
            //放行
            return chain.filter(exchange);
        }

        //2.从cookie中获取jti的值，如果该值不存在，拒绝本次访问
        String jti = authService.getJtiFromCookie(request);
        if (StringUtils.isEmpty(jti)) {
            //拒绝本次访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //3.从redis中获取jwt的值，如果该值不存在，拒绝本次访问
        String jwt = authService.getJwtFromRedis(jti);
        if (StringUtils.isEmpty(jwt)) {
            //拒绝访问
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //4.对当前的请求进行加强，让它会携带令牌的信息
        request.mutate().header("Authorization", "Bearer " + jwt);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
