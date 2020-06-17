package com.sumeng.peekshopping.search.service;

/**
 * @date: 2020/6/16 11:45
 * @author: sumeng
 */
public interface EsManagerService {

    /**
     * 创建索引库
     */
    void createIndexAndMapping();


    /**
     * 导入全部数据到ES索引库
     */
    void importAll();


    /**
     * 根据spuId导入数据到ES索引库
     *
     * @param spuId spuId
     */
    void importDataToEsBySpuId(String spuId);


    /**
     * 根据spuId从ES索引库中删除
     * @param spuId spuID
     */
    void delDataFromEsBySpuId(String spuId);
}
