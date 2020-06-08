package com.sumeng.peekshopping.goods.service;

import com.sumeng.peekshopping.goods.pojo.Brand;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/5 19:11
 * @author: sumeng
 */
public interface BrandService {

    /**
     * 查询所有
     *
     * @return
     */
    public List<Brand> findAll();


    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    public Brand findById(int id);


    /**
     * 添加品牌
     *
     * @param brand
     */
    public void insertBrand(Brand brand);


    /**
     * 修改品牌
     *
     * @param brand
     */
    public void updateBrand(Brand brand);

    /**
     * 删除品牌
     *
     * @param id
     */
    public void deleteById(int id);

    /**
     * 品牌列表查询
     */
    public List<Brand> list(Map<String,Object> searchMap);

    /**
     * 分页查询
     */
    public List<Brand> pageList(int page, int size);

    /**
     * 分页加条件查询
     */
    public List<Brand> pageList(Map<String,Object> searchMap, int page, int size);


    /**
     * 根据商品分类查询品牌列表
     */
    List<Map> findBrandListByCategoryName(String categoryName);

}
