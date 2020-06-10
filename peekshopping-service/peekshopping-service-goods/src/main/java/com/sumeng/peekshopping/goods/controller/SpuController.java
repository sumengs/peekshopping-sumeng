package com.sumeng.peekshopping.goods.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.goods.pojo.Goods;
import com.sumeng.peekshopping.goods.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @date: 2020/6/9 15:39
 * @author: sumeng
 */
@RestController
@RequestMapping("/spu")
public class SpuController {

    @Autowired
    private SpuService spuService;

    /**
     * 添加保存商品
     *
     * @param goods 商品信息
     * @return result
     */
    @PostMapping
    public Result<String> add(@RequestBody Goods goods) {

        spuService.add(goods);
        return new Result<>(true, StatusCode.OK, "添加成功");
    }


    /**
     * 根据Id查询商品详情
     *
     * @param id id
     * @return 商品详情
     */
    @GetMapping("/find/{id}")
    public Result<Goods> findGoodsById(@PathVariable String id) {
        Goods goods = spuService.findGoodsById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", goods);
    }


    /**
     * 更新商品详细信息
     *
     * @param goods 商品详细信息
     * @return result
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody Goods goods) {
        spuService.update(goods);
        return new Result<>(true, StatusCode.OK, "商品数据更新成功");
    }

    /**
     * 商品审核
     *
     * @param id 商品id
     * @return result
     */
    @PutMapping("/audit/{id}")
    public Result<String> audit(@PathVariable String id) {
        spuService.audit(id);
        return new Result<>(true, StatusCode.OK, "商品审核通过", "");
    }


    /**
     * 商品下架
     *
     * @param id 商品id
     * @return result
     */
    @PutMapping("/pull/{id}")
    public Result<String> pull(@PathVariable String id) {
        spuService.pull(id);
        return new Result<>(true, StatusCode.OK, "商品下架成功", "");
    }

    /**
     * 商品上架
     *
     * @param id 商品id
     * @return result
     */
    @PutMapping("/post/{id}")
    public Result<String> post(@PathVariable String id) {
        spuService.post(id);
        return new Result<>(true, StatusCode.OK, "商品上架成功", "");
    }


    /**
     * 逻辑删除
     *
     * @param id id
     * @return result
     */
    @PutMapping("/delete/{id}")
    public Result<String> delete(@PathVariable String id) {
        spuService.delete(id);
        return new Result<>(true, StatusCode.OK, "移入回收站成功");
    }


    /**
     * 商品还原
     *
     * @param id spuID
     * @return result
     */
    @PutMapping("/restore/{id}")
    public Result<String> restore(@PathVariable String id) {
        spuService.restore(id);
        return new Result<>(true, StatusCode.OK, "还原成功", "");
    }

    @PutMapping("/realDelete/{id}")
    public Result<String> realDelete(@PathVariable String id) {
        spuService.realDelete(id);
        return new Result<>(true, StatusCode.OK, "删除成功", "");
    }

}
