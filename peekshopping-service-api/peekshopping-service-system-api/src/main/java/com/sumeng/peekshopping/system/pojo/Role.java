package com.sumeng.peekshopping.system.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @date: 2020/6/9 18:57
 * @author: sumeng
 */

@Data
@Table(name = "tb_role")
public class Role implements Serializable {

    /**
     * ID
     */
    @Id
    private Integer id;


    /**
     * 角色名称
     */
    private String name;

}
