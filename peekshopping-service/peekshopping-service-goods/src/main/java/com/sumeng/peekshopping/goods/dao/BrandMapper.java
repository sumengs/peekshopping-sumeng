package com.sumeng.peekshopping.goods.dao;

import com.sumeng.peekshopping.goods.pojo.Brand;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/5 19:11
 * @author: sumeng
 */


public interface BrandMapper extends Mapper<Brand> {


    /**
     * 剧商品分类查询品牌列表
     *
     * @param categoryName 分类名称
     * @return 规格列表
     */
    @Select("SELECT * FROM tb_brand RIGHT OUTER JOIN " +
            "(SELECT tb_category_brand.brand_id FROM tb_category_brand RIGHT JOIN " +
            "(SELECT tb_category.id FROM tb_category WHERE tb_category.`name` = #{name}) AS tb1 ON tb_category_brand.category_id = tb1.id)" +
            " AS tb2 ON tb_brand.id = tb2.brand_id ORDER BY seq")
    List<Map> findBrandListByCategoryName(@Param("name") String categoryName);


}
