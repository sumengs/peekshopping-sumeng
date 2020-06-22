package com.sumeng.peekshopping.user.oauth.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.user.oauth.service.AuthService;
import com.sumeng.peekshopping.user.oauth.util.AuthToken;
import com.sumeng.peekshopping.user.oauth.util.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @date: 2020/6/22 9:28
 * @author: sumeng
 */
@Controller
@RequestMapping("/oauth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;


    @RequestMapping("/login")
    @ResponseBody
    public Result<String> login(String username, String password, HttpServletResponse response) {
        //校验参数

        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("请输入用户名");
        }


        if (StringUtils.isEmpty(password)) {
            throw new RuntimeException("请输入密码");
        }


        //申请令牌 auth token

        AuthToken authToken = authService.login(username, password, clientId, clientSecret);

        //将jti的值存入cookie中

        this.saveJti2Cookie(authToken.getJti(), response);

        //返回结果
        return new Result<>(true, StatusCode.OK, "登陆成功", authToken.getJti());

    }

    private void saveJti2Cookie(String jti, HttpServletResponse response) {
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", jti, cookieMaxAge, false);

    }


    @RequestMapping("/toLogin")
    protected String toLogin() {
        return "login";
    }
}
