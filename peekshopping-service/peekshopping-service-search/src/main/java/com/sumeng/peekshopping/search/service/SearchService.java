package com.sumeng.peekshopping.search.service;

import java.util.Map;

/**
 * @date: 2020/6/17 19:37
 * @author: sumeng
 */
public interface SearchService {


    /**
     * 按照查询条件进行数据查询
     * @param searchMap 查询条件
     * @return 查询结果
     */
    Map search(Map<String,String> searchMap);


}
