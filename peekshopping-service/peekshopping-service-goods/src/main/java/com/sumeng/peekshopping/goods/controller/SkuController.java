package com.sumeng.peekshopping.goods.controller;

import com.sumeng.peekshopping.goods.pojo.Sku;
import com.sumeng.peekshopping.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/16 11:22
 * @author: sumeng
 */
@RestController
@RequestMapping("/sku")
public class SkuController {


    @Autowired
    private SkuService skuService;


    @GetMapping("/spu/{spuId}")
    public List<Sku> findListBySpuId(@PathVariable("spuId") String spuId) {
        Map<String, Object> paramMap = new HashMap<>();

        String all = "all";

        if (!all.equals(spuId)) {
            paramMap.put("spuId", spuId);
        }

        paramMap.put("status", "1");

        return skuService.findList(paramMap);


    }
}
