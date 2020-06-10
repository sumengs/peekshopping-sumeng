package com.sumeng.peekshopping.goods.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.goods.pojo.Brand;
import com.sumeng.peekshopping.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/5 19:12
 * @author: sumeng
 */
@CrossOrigin
@RestController
@RequestMapping("/brand")
public class BrandController {


    /**
     * set注入
     */
    private BrandService brandService;

    @Autowired
    public void setBrandService(BrandService brandService) {
        this.brandService = brandService;
    }

    /**
     * 查询所有品牌信息
     *
     * @return 返回所有品牌信息
     */
    @GetMapping("/all")
    public Result<List<Brand>> findAllBrand() {
        List<Brand> brandList = brandService.findAllBrand();
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }


    /**
     * 根据id查询
     *
     * @param id id
     * @return 单个品牌信息
     */
    @GetMapping("/{id}")
    public Result<Brand> findBrandById(@PathVariable Integer id) {
        Brand brand = brandService.findBrandById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * 添加品牌信息
     *
     * @param brand 品牌信息
     * @return 添加状态
     */
    @PostMapping
    public Result<Array> insertBrand(@RequestBody Brand brand) {
        brandService.insertBrand(brand);
        return new Result<>(true, StatusCode.OK, "添加成功", null);
    }

    /**
     * 修改品牌信息
     *
     * @param brand 品牌
     * @param id    品牌id
     * @return 修改状态
     */
    @PutMapping("/{id}")
    public Result<Array> updateBrand(@RequestBody Brand brand,
                                     @PathVariable Integer id) {
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result<>(true, StatusCode.OK, "修改成功", null);
    }

    /**
     * 删除品牌信息
     *
     * @param id 品牌id
     * @return 删除状态
     */
    @DeleteMapping("/{id}")
    public Result<Array> deleteBrand(@PathVariable Integer id) {
        brandService.deleteBrandById(id);
        return new Result<>(true, StatusCode.OK, "删除成功", null);
    }

    /**
     * 根据条件查询
     *
     * @param searchMap 查询条件
     * @return 查询结果
     */
    @GetMapping("/search")
    public Result<List<Brand>> searchBrandList(@RequestParam Map<String, Object> searchMap) {
        List<Brand> brandList = brandService.searchBrandList(searchMap);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }


    /**
     * 分页查询
     *
     * @param page 页面数
     * @param size 每页显示数量
     * @return 分页查询结果
     */
    @GetMapping("/search/all/{page}/{size}")
    public Result<List<Brand>> pageList(@PathVariable("page") Integer page,
                                        @PathVariable("size") Integer size) {
        List<Brand> brandList = brandService.pageList(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }

    /**
     * 分页条件查询
     *
     * @param searchMap 查询条件
     * @param page      页面数
     * @param size      每页显示数量
     * @return 分页条件查询结果
     */
    @GetMapping("/search/{page}/{size}")
    public Result<List<Brand>> pageList(@RequestParam Map<String, Object> searchMap,
                                        @PathVariable("page") Integer page,
                                        @PathVariable("size") Integer size) {
        List<Brand> brandList = brandService.pageList(searchMap, page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }

    /**
     * 根据分类名称查询品牌列表
     *
     * @param category 分类名称
     * @return brandList
     */
    @GetMapping("/category/{category}")
    public Result<List<Map>> findBrandListByCategoryName(@PathVariable String category) {
        List<Map> brandList = brandService.findBrandListByCategoryName(category);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }


}
