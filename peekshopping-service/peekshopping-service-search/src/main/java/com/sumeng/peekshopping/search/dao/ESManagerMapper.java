package com.sumeng.peekshopping.search.dao;

import com.sumeng.peekshopping.search.pojo.SkuInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @date: 2020/6/16 11:44
 * @author: sumeng
 */
public interface ESManagerMapper extends ElasticsearchRepository<SkuInfo,Long> {
}
