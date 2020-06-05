package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @date: 2020/6/5 19:01
 * @author: sumeng
 */

@Data
@Table(name = "tb_brand")
public class Brand {

    @Id
    private Integer id;     //品牌id
    private String name;    //品牌名称
    private String image;   //品牌的图片地址
    private String letter;  //品牌的首字母
    private Integer seq;    //排序


}
