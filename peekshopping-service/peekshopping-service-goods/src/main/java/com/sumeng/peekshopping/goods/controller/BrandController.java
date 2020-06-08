package com.sumeng.peekshopping.goods.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.goods.pojo.Brand;
import com.sumeng.peekshopping.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @Autowired
    private BrandService brandService;

    /**
     * 查询所有品牌信息
     *
     * @return 返回所有品牌信息
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brandList = brandService.findAll();
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }


    /**
     * 根据id查询
     *
     * @param id id
     * @return 单个品牌信息
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "查询成功", brand);
    }

    /**
     * 添加品牌信息
     *
     * @param brand 品牌新
     * @return result
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.insertBrand(brand);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改品牌信息
     *
     * @param brand 品牌
     * @param id    品牌id
     * @return result
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand,
                         @PathVariable Integer id) {
        brand.setId(id);
        brandService.updateBrand(brand);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除品牌信息
     *
     * @param id 品牌id
     * @return result
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        brandService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @GetMapping("/search")
    public Result<List<Brand>> search(@RequestParam Map<String, Object> searchMap) {
        List<Brand> brandList = brandService.list(searchMap);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }


    /**
     * 分页查询
     */
    @GetMapping("/search/all/{page}/{size}")
    public Result<List<Brand>> pageList(@PathVariable("page") Integer page,
                                        @PathVariable("size") Integer size) {
        List<Brand> brandList = brandService.pageList(page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }

    /**
     * 分页+条件查询
     */
    @GetMapping("/search/{page}/{size}")
    public Result<List<Brand>> pageList(@RequestParam Map<String, Object> searchMap,
                                        @PathVariable("page") Integer page,
                                        @PathVariable("size") Integer size) {
        List<Brand> brandList = brandService.pageList(searchMap,page, size);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }
}
