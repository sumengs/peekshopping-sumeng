package com.sumeng.peekshopping.goods.service;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/8 18:56
 * @author: sumeng
 */
public interface SpecService {

    /**
     * 剧商品分类查询商品规格列表
     *
     * @param categoryName 分类名称
     * @return 规格列表
     */
    List<Map> findSpecListByCategoryName(String categoryName);
}
