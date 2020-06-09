package com.sumeng.peekshopping.system.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 管理员账号表
 *
 * @date: 2020/6/9 10:17
 * @author: sumeng
 */
@Table(name = "tb_admin")
@Data
public class Admin implements Serializable {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private String status;
}
