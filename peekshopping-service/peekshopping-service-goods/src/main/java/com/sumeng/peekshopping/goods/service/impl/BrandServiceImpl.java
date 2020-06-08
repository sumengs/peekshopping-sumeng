package com.sumeng.peekshopping.goods.service.impl;

import com.github.pagehelper.PageHelper;
import com.sumeng.peekshopping.goods.dao.BrandMapper;
import com.sumeng.peekshopping.goods.pojo.Brand;
import com.sumeng.peekshopping.goods.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/5 19:12
 * @author: sumeng
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有品牌信息
     *
     * @return 所有品牌信息
     */
    @Override
    public List<Brand> findAllBrand() {
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询品牌信息
     *
     * @param id 品牌id
     * @return 品牌信息
     */
    @Override
    public Brand findBrandById(int id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加品牌信息
     *
     * @param brand 品牌信息
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void insertBrand(Brand brand) {
        brandMapper.insertSelective(brand);


    }

    /**
     * 更新品牌信息
     *
     * @param brand 品牌信息
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除品牌信息
     *
     * @param id 品牌信息
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deleteBrandById(int id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 品牌列表条件查询
     *
     * @param searchMap 查询条件
     * @return 品牌信息
     */
    @Override
    public List<Brand> searchBrandList(Map<String, Object> searchMap) {
        Example example = brandExample(searchMap);
        return brandMapper.selectByExample(example);
    }


    /**
     * 分页查询
     *
     * @param page 页面
     * @param size 每页的数据
     * @return
     */
    @Override
    public List<Brand> pageList(int page, int size) {
        PageHelper.startPage(page, size);
        return brandMapper.selectAll();
    }


    /**
     * 分页+条件查询
     *
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Brand> pageList(Map<String, Object> searchMap, int page, int size) {
        Example example = brandExample(searchMap);
        PageHelper.startPage(page, size);
        return brandMapper.selectByExample(example);
    }

    /**
     * 根据商品分类查询品牌列表
     *
     * @param categoryName 分类名称
     * @return 品牌列表
     */
    @Override
    public List<Map> findBrandListByCategoryName(String categoryName) {
        return brandMapper.findBrandListByCategoryName(categoryName);
    }


    private Example brandExample(Map<String, Object> searchMap) {
        Example example = new Example(Brand.class);
        //封装查询条件
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            //品牌名称（模糊）like
            if (searchMap.get("name") != null && !searchMap.get("name").equals("")) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            //品牌首字母精确查询
            if (searchMap.get("letter") != null && !searchMap.get("letter").equals("")) {
                criteria.andEqualTo("letter", searchMap.get("letter"));
            }
        }

        return example;
    }
}
