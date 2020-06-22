package com.sumeng.peekshopping.user.service.impl;

import com.sumeng.peekshopping.user.dao.UserMapper;
import com.sumeng.peekshopping.user.pojo.User;
import com.sumeng.peekshopping.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @date: 2020/6/20 19:36
 * @author: sumeng
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 根据Id查询
     *
     * @param id UserID
     * @return User
     */
    @Override
    public User findById(String id) {
        return userMapper.selectByPrimaryKey(id);


    }

    /**
     * 查询所有
     *
     * @return UserList
     */
    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }
}
