package com.sumeng.peekshopping.search.pojo;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;
import java.util.Map;

/**
 * @date: 2020/6/16 10:15
 * @author: sumeng
 */
@Document(indexName = "skuinfo", type = "docs")
@Data
public class SkuInfo {

    /**
     * 商品id，同时也是商品编号
     */
    @Id
    @Field(index = true, store = true, type = FieldType.Keyword)
    private Long id;

    /**
     * sku 名称
     */
    @Field(index = true, store = true, type = FieldType.Text, analyzer = "ik_smart")
    private String name;

    /**
     * 商品价格，单位为元
     */
    @Field(index = true, store = true, type = FieldType.Double)
    private Long price;

    /**
     * 库存数量
     */
    @Field(index = true, store = true, type = FieldType.Integer)
    private Integer num;

    /**
     * 商品图片
     */
    @Field(index = true, store = true, type = FieldType.Text)
    private String image;

    /**
     * 商品状态
     * 1-正常，2-下架，3-删除
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String status;

    /**
     * 创建时间
     */
    private Date crateTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否默认
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String isDefault;

    /**
     * Spu Id
     */
    @Field(index = true, store = true, type = FieldType.Long)
    private Long spuId;

    /**
     * 类目录Id
     */
    @Field(index = true, store = true, type = FieldType.Long)
    private Long categoryId;

    /**
     * 类目录名称
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String categoryName;


    /**
     * 品牌名称
     */
    @Field(index = true, store = true, type = FieldType.Keyword)
    private String brandName;

    /**
     * 规格
     */
    private String spec;

    /**
     * 规格参数
     */
    private Map<String, Object> specMap;
}
