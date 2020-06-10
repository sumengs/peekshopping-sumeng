package com.sumeng.peekshopping.goods.service;

import com.sumeng.peekshopping.goods.pojo.Goods;

/**
 * @date: 2020/6/10 9:03
 * @author: sumeng
 */
public interface SpuService {

    /**
     * 添加保存商品
     *
     * @param goods 商品组合实体类
     */
    void add(Goods goods);


    /**
     * 根据Id查询商品
     *
     * @param id 商品id
     * @return 商品
     */
    Goods findGoodsById(String id);

    /**
     * 修改数据
     *
     * @param goods 商品数据
     */
    void update(Goods goods);

    /**
     * 商品审核
     *
     * @param id spuID
     */
    void audit(String id);


    /**
     * 商品下架
     *
     * @param id spuID
     */
    void pull(String id);


    /**
     * 商品上架
     *
     * @param id spuID
     */
    void post(String id);


    /**
     * 逻辑删除
     *
     * @param id supId
     */
    void delete(String id);


    /**
     * 还原数据
     *
     * @param id spuID
     */
    void restore(String id);


    /**
     * 物理删除
     *
     * @param id spu ID
     */
    void realDelete(String id);

}
