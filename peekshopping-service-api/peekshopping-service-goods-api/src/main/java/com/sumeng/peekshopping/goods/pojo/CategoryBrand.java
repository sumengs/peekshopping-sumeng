package com.sumeng.peekshopping.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 品牌分类关系表
 *
 * @date: 2020/6/9 15:11
 * @author: sumeng
 */
@Data
@Table(name = "tb_category_brand")
public class CategoryBrand {

    /**
     * 分类id
     */
    @Id
    private Integer categoryId;

    /**
     * 品牌ID
     */
    @Id
    private Integer brandId;
}
