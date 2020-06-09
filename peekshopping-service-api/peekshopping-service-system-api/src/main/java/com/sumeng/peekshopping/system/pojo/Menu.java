package com.sumeng.peekshopping.system.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Menu实体类
 *
 * @date: 2020/6/9 15:30
 * @author: sumeng
 */
@Data
@Table(name = "tb_menu")
public class Menu {

    /**
     * 菜单Id
     */
    @Id
    private String id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * URL
     */
    private String url;

    /**
     * 上级菜单ID
     */
    private String parentId;
}
