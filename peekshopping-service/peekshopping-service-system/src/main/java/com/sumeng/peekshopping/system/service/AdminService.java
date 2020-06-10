package com.sumeng.peekshopping.system.service;

import com.sumeng.peekshopping.system.pojo.Admin;

/**
 * @date: 2020/6/9 10:13
 * @author: sumeng
 */

public interface AdminService {

    /**
     * 管理员登陆方法
     *
     * @param admin 管理员用户信息
     * @return 是否成功
     */
    boolean login(Admin admin);


    /**
     * 增加管理员
     * @param admin 管理员信息
     */
    void add(Admin admin);

}
