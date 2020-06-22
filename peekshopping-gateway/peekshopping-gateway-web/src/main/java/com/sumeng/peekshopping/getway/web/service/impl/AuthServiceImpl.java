package com.sumeng.peekshopping.getway.web.service.impl;

import com.sumeng.peekshopping.getway.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

/**
 * @date: 2020/6/22 10:10
 * @author: sumeng
 */
@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 从令牌中获取jti的值
     *
     * @param request request
     * @return jti
     */
    @Override
    public String getJtiFromCookie(ServerHttpRequest request) {
        HttpCookie cookie = request.getCookies().getFirst("uid");
        if (cookie != null) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 从redis中获取jwt
     *
     * @param jti jti
     * @return jwt
     */
    @Override
    public String getJwtFromRedis(String jti) {
        return stringRedisTemplate.boundValueOps(jti).get();
    }
}
