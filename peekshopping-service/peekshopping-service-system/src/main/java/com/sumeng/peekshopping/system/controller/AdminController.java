package com.sumeng.peekshopping.system.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.system.pojo.Admin;
import com.sumeng.peekshopping.system.service.AdminService;
import com.sumeng.peekshopping.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * admin controller层
 *
 * @date: 2020/6/9 19:21
 * @author: sumeng
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     * @return result
     */
    @PostMapping("/add")
    public Result<String> add(@RequestBody Admin admin) {
        adminService.add(admin);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }


    /**
     * 登陆
     *
     * @param admin 管理员信息
     * @return 管理员账号和jwt令牌
     */
    @PostMapping("/login")
    public Result<Map<String, String>> login(@RequestBody Admin admin) {
        boolean result = adminService.login(admin);
        if (result) {
            //密码正确，生成jwt令牌返回客户端
            Map<String, String> info = new HashMap<>();
            info.put("username", admin.getLoginName());
            //基于工具类生成jwt令牌
            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), admin.getLoginName(), null);
            info.put("token", jwt);
            return new Result<>(true, StatusCode.OK, "登陆成功", info);
        } else {
            return new Result<>(false, StatusCode.ERROR, "登陆失败");
        }
    }


}
