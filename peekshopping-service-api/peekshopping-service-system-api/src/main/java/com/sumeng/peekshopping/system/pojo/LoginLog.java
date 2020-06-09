package com.sumeng.peekshopping.system.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 登陆日志表
 *
 * @date: 2020/6/9 15:25
 * @author: sumeng
 */
@Data
@Table(name = "tb_login_log")
public class LoginLog {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 登陆名
     */
    private String loginName;

    /**
     * 登陆ip
     */
    private String ip;

    /**
     * 登陆浏览器名
     */
    private String browserName;

    /**
     * 地区
     */
    private String location;

    /**
     * 登陆时间
     */
    private java.util.Date loginTime;
}
