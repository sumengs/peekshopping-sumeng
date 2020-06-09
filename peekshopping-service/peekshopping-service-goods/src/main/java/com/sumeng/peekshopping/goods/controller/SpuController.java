package com.sumeng.peekshopping.goods.controller;

import com.sumeng.peekshopping.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2020/6/9 15:39
 * @author: sumeng
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;
}
