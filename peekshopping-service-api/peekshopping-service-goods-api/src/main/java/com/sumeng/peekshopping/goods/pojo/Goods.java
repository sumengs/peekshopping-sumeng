package com.sumeng.peekshopping.goods.pojo;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * 商品组合实体类
 *
 * @date: 2020/6/10 9:00
 * @author: sumeng
 */
@Data
public class Goods implements Serializable {

    /**
     * 标准产品单位
     */
    @Id
    private Spu spu;

    /**
     * 库存量单位
     */
    @Id
    private List<Sku> skuList;
}
