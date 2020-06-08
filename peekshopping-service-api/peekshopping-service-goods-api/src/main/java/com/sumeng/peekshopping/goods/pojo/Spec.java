package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 规格表
 *
 * @date: 2020/6/6 20:21
 * @author: sumeng
 */
@Table(name = "tb_spec")
@Data
public class Spec implements Serializable {

    /**
     * ID
     */
    @Id
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 规格选项
     */
    private String options;

    /**
     * 排序
     */
    private Integer seq;

    /**
     * 模板ID
     */
    private Integer templateId;


}
