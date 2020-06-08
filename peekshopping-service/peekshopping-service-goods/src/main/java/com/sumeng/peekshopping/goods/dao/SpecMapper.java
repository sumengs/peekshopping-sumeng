package com.sumeng.peekshopping.goods.dao;

import com.sumeng.peekshopping.goods.pojo.Spec;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/8 18:56
 * @author: sumeng
 */
public interface SpecMapper extends Mapper<Spec> {

    /**
     * 根据剧商品分类查询商品规格列表
     *
     * @param categoryName 分类名称
     * @return 规格列表
     */
    @Select("SELECT `name`,`options` FROM tb_spec WHERE template_id in (SELECT template_id FROM tb_category WHERE name = #{name})")
    List<Map> findSpecListByCategoryName(@Param("name") String categoryName);
}
