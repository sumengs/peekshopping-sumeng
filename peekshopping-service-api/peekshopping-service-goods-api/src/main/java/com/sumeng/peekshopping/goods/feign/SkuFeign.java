package com.sumeng.peekshopping.goods.feign;

import com.sumeng.peekshopping.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @date: 2020/6/16 11:40
 * @author: sumeng
 */

@FeignClient(name = "goods-service")
public interface SkuFeign {


    /**
     * 多条件搜索品牌数据
     *
     * @param spuId spuID
     * @return skuList
     */
    @GetMapping("/sku/spu/{spuId}")
    List<Sku> findSkuListBySpuId(@PathVariable("spuId") String spuId);
}
