package com.sumeng.peekshopping.user.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.user.pojo.User;
import com.sumeng.peekshopping.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @date: 2020/6/20 19:46
 * @author: sumeng
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    @GetMapping
    @PreAuthorize("hasAnyAuthority('seckill_list')")
    public Result<User> findAll() {
        List<User> userList = userService.findAll();

        return new Result<>(true, StatusCode.OK, "查询所有用户成功", userList);
    }


    /**
     * 根据Id查询
     *
     * @param username id
     * @return User
     */
    @GetMapping("/load/{username}")
    public User findUserInfo(@PathVariable("username") String username) {
        return userService.findById(username);
    }

}
