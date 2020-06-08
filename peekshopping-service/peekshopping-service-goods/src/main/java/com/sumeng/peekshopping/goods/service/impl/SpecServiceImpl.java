package com.sumeng.peekshopping.goods.service.impl;

import com.sumeng.peekshopping.goods.dao.SpecMapper;
import com.sumeng.peekshopping.goods.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/8 18:56
 * @author: sumeng
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;


    /**
     * 剧商品分类查询商品规格列表
     *
     * @param categoryName 分类名称
     * @return 规格列表
     */
    @Override
    public List<Map> findSpecListByCategoryName(String categoryName) {
        return specMapper.findSpecListByCategoryName(categoryName);
    }
}
