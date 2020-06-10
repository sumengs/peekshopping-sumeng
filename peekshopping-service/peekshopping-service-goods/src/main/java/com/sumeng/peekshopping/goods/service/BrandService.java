package com.sumeng.peekshopping.goods.service;

import com.sumeng.peekshopping.goods.pojo.Brand;

import java.util.List;
import java.util.Map;

/**
 * 品牌Service接口
 *
 * @date: 2020/6/5 19:11
 * @author: sumeng
 */
public interface BrandService {

    /**
     * 查询所有品牌信息
     *
     * @return 所有品牌信息
     */
    List<Brand> findAllBrand();


    /**
     * 根据品牌id查询品牌信息
     *
     * @param id 品牌id
     * @return 品牌信息
     */
    Brand findBrandById(int id);


    /**
     * 添加品牌信息
     *
     * @param brand 品牌信息
     */
    void insertBrand(Brand brand);


    /**
     * 修改品牌信息
     *
     * @param brand 品牌信息
     */
    void updateBrand(Brand brand);

    /**
     * 删除品牌
     *
     * @param id 品牌id
     */
    void deleteBrandById(int id);

    /**
     * 按条件查询品牌信息
     *
     * @param searchMap 查询条件
     * @return 查询结果
     */
    List<Brand> searchBrandList(Map<String, Object> searchMap);


    /**
     * 分页查询所有品牌新
     *
     * @param page 页码
     * @param size 每页显示数量
     * @return 查询结果
     */
    List<Brand> pageList(int page, int size);


    /**
     * 分页加条件查询
     *
     * @param searchMap 查询条件
     * @param page      页码
     * @param size      每页显示数量
     * @return 查询结果
     */
    List<Brand> pageList(Map<String, Object> searchMap, int page, int size);


    /**
     * 根据商品分类查询品牌列表
     *
     * @param categoryName 分类名称
     * @return 查询结果
     */
    List<Map> findBrandListByCategoryName(String categoryName);


}
