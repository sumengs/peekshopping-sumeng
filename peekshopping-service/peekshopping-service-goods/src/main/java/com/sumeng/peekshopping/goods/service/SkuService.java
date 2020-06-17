package com.sumeng.peekshopping.goods.service;

import com.sumeng.peekshopping.goods.pojo.Sku;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/16 11:23
 * @author: sumeng
 */
public interface SkuService {

    /**
     * 多条件搜索
     * * @param searchMap
     * * @return list
     */
    List<Sku> findList(Map<String, Object> searchMap);

}
