package com.sumeng.peekshopping.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.sumeng.peekshopping.goods.feign.SkuFeign;
import com.sumeng.peekshopping.goods.pojo.Sku;
import com.sumeng.peekshopping.search.dao.ESManagerMapper;
import com.sumeng.peekshopping.search.pojo.SkuInfo;
import com.sumeng.peekshopping.search.service.EsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/16 11:49
 * @author: sumeng
 */
@Service
public class EsManagerServiceImpl implements EsManagerService {


    @Autowired
    private ElasticsearchTemplate elasticsearchRestTemplate;

    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private ESManagerMapper esManagerMapper;


    /**
     * 创建索引库
     */
    @Override
    public void createIndexAndMapping() {
        //创建索引
        elasticsearchRestTemplate.createIndex(SkuInfo.class);
        //创建映射
        elasticsearchRestTemplate.putMapping(SkuInfo.class);

    }


    /**
     * 导入全部sku集合到索引库
     */
    @Override
    public void importAll() {

        //查询sku集合
        List<Sku> skuList = skuFeign.findSkuListBySpuId("all");
        if (skuList == null || skuList.size() <= 0) {
            throw new RuntimeException("当前没有数据被查询到，无法导入索引库");
        }

        //skuList转换为json
        String skuJsonList = JSON.toJSONString(skuList);
        //将Json转为skuInfo
        List<SkuInfo> skuInfoList = JSON.parseArray(skuJsonList, SkuInfo.class);


        for (SkuInfo skuInfo : skuInfoList) {
            //将规格信息转换为map
            Map specMap = JSON.parseObject(skuInfo.getSpec(), Map.class);
            skuInfo.setSpecMap(specMap);
        }

        // 导入索引库
        esManagerMapper.saveAll(skuInfoList);


    }


    /**
     * 根据spuId查询skuList，添加到索引库
     *
     * @param spuId spuId
     */
    @Override
    public void importDataToEsBySpuId(String spuId) {
        List<Sku> skuList = skuFeign.findSkuListBySpuId(spuId);

        if (skuList == null || skuList.size() <= 0) {
            throw new RuntimeException("当前没有数据被查询到，无法导入索引库");
        }

        //将集合转为JSON
        String skuListJson = JSON.toJSONString(skuList);

        List<SkuInfo> skuInfoList = JSON.parseArray(skuListJson, SkuInfo.class);

        for (SkuInfo skuInfo : skuInfoList) {
            //将规格信息转换为map
            Map specMap = JSON.parseObject(skuInfo.getSpec(), Map.class);

            skuInfo.setSpecMap(specMap);
        }

        //添加到索引库
        esManagerMapper.saveAll(skuInfoList);

    }


    /**
     * 根据spuId从ES索引库中删除
     *
     * @param spuId spuID
     */
    @Override
    public void delDataFromEsBySpuId(String spuId) {

        List<Sku> skuList = skuFeign.findSkuListBySpuId(spuId);

        if (skuList == null || skuList.size() <= 0) {
            throw new RuntimeException("当前没有数据被查询到，无法导入索引库");
        }
        for (Sku sku : skuList) {
            esManagerMapper.deleteById(Long.parseLong(sku.getId()));
        }


    }
}
