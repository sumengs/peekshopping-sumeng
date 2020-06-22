package com.sumeng.peekshopping.user.service;

import com.sumeng.peekshopping.user.pojo.User;

import java.util.List;

/**
 * User service 接口
 *
 * @date: 2020/6/20 19:34
 * @author: sumeng
 */
public interface UserService {

    /**
     * 根据Id查询
     * @param id UserID
     * @return User
     */
    User findById(String id);


    /**
     * 查询所有
     * @return userList
     */
    List<User> findAll();
}
