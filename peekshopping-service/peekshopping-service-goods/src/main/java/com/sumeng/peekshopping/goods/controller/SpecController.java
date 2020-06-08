package com.sumeng.peekshopping.goods.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.goods.pojo.Spec;
import com.sumeng.peekshopping.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/8 18:55
 * @author: sumeng
 */
@RestController
@RequestMapping("/spec")
public class SpecController {


    @Autowired
    private SpecService specService;


    /**
     * 剧商品分类查询商品规格列表
     * @param category 商品分类
     * @return 规格列表
     */
    @GetMapping("category/{category}")
    public Result<List<Spec>> findSpecListByCategoryName(@PathVariable String category) {
        List<Map> specList = specService.findSpecListByCategoryName(category);
        return new Result<>(true, StatusCode.OK, "查询成功", specList);
    }

}
