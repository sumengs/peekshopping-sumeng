package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 品牌表
 *
 * @date: 2020/6/5 19:01
 * @author: sumeng
 */

@Data
@Table(name = "tb_brand")
public class Brand implements Serializable {

    /**
     * 品牌id
     */
    @Id
    private Integer id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌的图片地址
     */
    private String image;

    /**
     * 品牌的首字母
     */
    private String letter;

    /**
     * 排序
     */
    private Integer seq;


}
