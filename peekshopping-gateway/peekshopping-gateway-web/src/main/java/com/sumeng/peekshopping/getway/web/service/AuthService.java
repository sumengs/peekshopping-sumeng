package com.sumeng.peekshopping.getway.web.service;

import org.springframework.http.server.reactive.ServerHttpRequest;

/**
 * @date: 2020/6/22 10:09
 * @author: sumeng
 */
public interface AuthService {

    /**
     * 从cookie中获取jti的值
     * @param request request
     * @return jti
     */
    String getJtiFromCookie(ServerHttpRequest request);

    /**
     * 从redis中获取jwt
     * @param jti jti
     * @return jwt
     */
    String getJwtFromRedis(String jti);
}
