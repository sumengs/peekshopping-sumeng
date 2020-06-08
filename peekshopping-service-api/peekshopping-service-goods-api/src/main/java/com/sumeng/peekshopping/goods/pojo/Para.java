package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 参数表
 * @date: 2020/6/6 20:21
 * @author: sumeng
 */

@Table(name = "tb_para")
@Data
public class Para implements Serializable {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 选项
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
