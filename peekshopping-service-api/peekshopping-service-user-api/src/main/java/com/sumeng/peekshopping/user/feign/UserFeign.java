package com.sumeng.peekshopping.user.feign;

import com.sumeng.peekshopping.user.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @date: 2020/6/20 19:27
 * @author: sumeng
 */
@FeignClient(name = "user-service")
public interface UserFeign {

    /**
     * 根据Id查询
     *
     * @param username id
     * @return User
     */
    @GetMapping("/user/load/{username}")
    public User findUserInfo(@PathVariable("username") String username);
}
