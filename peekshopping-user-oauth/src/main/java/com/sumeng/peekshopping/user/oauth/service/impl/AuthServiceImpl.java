package com.sumeng.peekshopping.user.oauth.service.impl;

import com.sumeng.peekshopping.user.oauth.service.AuthService;
import com.sumeng.peekshopping.user.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @date: 2020/6/22 9:15
 * @author: sumeng
 */
@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${auth.ttl}")
    private long ttl;

    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) {
        //1. 申请令牌
        //构建请求地址  http://localhost:8301/oauth/token
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-oauth");
        // http://localhost:8301
        URI uri = serviceInstance.getUri();
        // http://localhost:8301/oauth/token
        String url = uri + "/oauth/token";

        // 封装请求参数 body , headers
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", this.getHttpBasic(clientId, clientSecret));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        //当后端出现了401,400.后端不对着两个异常编码进行处理,而是直接返回给前端
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        //发送请求
        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        Map map = responseEntity.getBody();
        if (map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null) {
            //申请令牌失败
            throw new RuntimeException("申请令牌失败");
        }

        //2. 封装结果数据
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) map.get("access_token"));
        authToken.setRefreshToken((String) map.get("refresh_token"));
        authToken.setJti((String) map.get("jti"));


        //3. 将jti作为redis中的key，将jwt作为redis的value返回
        stringRedisTemplate.boundValueOps(authToken.getJti()).set(authToken.getAccessToken(), ttl, TimeUnit.SECONDS);
        return authToken;


    }

    private String getHttpBasic(String clientId, String clientSecret) {
        String value = clientId + ":" + clientSecret;
        byte[] encode = Base64Utils.encode(value.getBytes());
        //Basic Y2hhbmdnb3U6Y2hhbmdnb3U=
        return "Basic " + new String(encode);
    }
}

