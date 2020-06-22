package com.sumeng.peekshopping.user.oauth.service;

import com.sumeng.peekshopping.user.oauth.util.AuthToken;

/**
 * @date: 2020/6/22 9:13
 * @author: sumeng
 */
public interface AuthService {

    AuthToken login(String username, String password, String clientId, String clientSecret);
}
