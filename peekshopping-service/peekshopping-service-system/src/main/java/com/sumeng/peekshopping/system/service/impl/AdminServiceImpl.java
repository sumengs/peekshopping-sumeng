package com.sumeng.peekshopping.system.service.impl;

import com.sumeng.peekshopping.system.dao.AdminMapper;
import com.sumeng.peekshopping.system.pojo.Admin;
import com.sumeng.peekshopping.system.service.AdminService;
import com.sumeng.peekshopping.system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


/**
 * @date: 2020/6/9 18:59
 * @author: sumeng
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登陆
     *
     * @param admin 管理员用户信息
     * @return 是否登陆成功
     */
    @Override
    public boolean login(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setLoginName(admin.getLoginName());
        admin1.setStatus("1");

        Admin admin2 = adminMapper.selectOne(admin1);

        if (admin2 == null) {
            return false;
        } else {
            //验证密码，Bcrypt为spring的包，第一个参数为明文密码，第二个参数为密文面
            return BCrypt.checkpw(admin.getPassword(), admin2.getPassword());
        }
    }

    /**
     * 添加管理员
     *
     * @param admin 管理员信息
     */
    @Override
    public void add(Admin admin) {
        String password = BCrypt.hashpw(admin.getPassword(), BCrypt.gensalt());
        admin.setPassword(password);

        adminMapper.insertSelective(admin);
    }
}
