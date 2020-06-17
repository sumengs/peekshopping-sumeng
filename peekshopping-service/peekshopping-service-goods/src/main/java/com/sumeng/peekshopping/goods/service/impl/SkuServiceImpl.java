package com.sumeng.peekshopping.goods.service.impl;

import com.sumeng.peekshopping.constant.GoodsStatus;
import com.sumeng.peekshopping.goods.dao.SkuMapper;
import com.sumeng.peekshopping.goods.pojo.Sku;
import com.sumeng.peekshopping.goods.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/16 11:23
 * @author: sumeng
 */
@Service
public class SkuServiceImpl implements SkuService {


    @Autowired
    private SkuMapper skuMapper;


    @Override
    public List<Sku> findList(Map<String, Object> searchMap) {

        Example example = createExample(searchMap);
        return skuMapper.selectByExample(example);
    }


    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 商品id
            if (searchMap.get(GoodsStatus.Id) != null && !"".equals(searchMap.get(GoodsStatus.Id))){
                criteria.andEqualTo(GoodsStatus.Id, searchMap.get(GoodsStatus.Id));
            }
            // 商品条码
            if (searchMap.get(GoodsStatus.Sn) != null && !"".equals(searchMap.get(GoodsStatus.Sn))) {
                criteria.andEqualTo(GoodsStatus.Sn, searchMap.get(GoodsStatus.Sn));
            }
            // SKU名称
            if (searchMap.get(GoodsStatus.Name) != null && !"".equals(searchMap.get(GoodsStatus.Name))) {
                criteria.andLike(GoodsStatus.Name, "%" + searchMap.get(GoodsStatus.Name) + "%");
            }
            // 商品图片
            if (searchMap.get(GoodsStatus.Image) != null && !"".equals(searchMap.get(GoodsStatus.Image))) {
                criteria.andLike(GoodsStatus.Image, "%" + searchMap.get(GoodsStatus.Image) + "%");
            }
            // 商品图片列表
            if (searchMap.get(GoodsStatus.Images) != null && !"".equals(searchMap.get(GoodsStatus.Images))) {
                criteria.andLike(GoodsStatus.Images, "%" + searchMap.get(GoodsStatus.Images) + "%");
            }
            // SPUID
            if (searchMap.get(GoodsStatus.SpuId) != null && !"".equals(searchMap.get(GoodsStatus.SpuId))) {
                criteria.andEqualTo(GoodsStatus.SpuId, searchMap.get(GoodsStatus.SpuId));
            }
            // 类目名称
            if (searchMap.get(GoodsStatus.CategoryName) != null && !"".equals(searchMap.get(GoodsStatus.CategoryName))) {
                criteria.andLike(GoodsStatus.CategoryName, "%" + searchMap.get(GoodsStatus.CategoryName) + "%");
            }
            // 品牌名称
            if (searchMap.get(GoodsStatus.BrandName) != null && !"".equals(searchMap.get(GoodsStatus.BrandName))) {
                criteria.andLike(GoodsStatus.BrandName, "%" + searchMap.get(GoodsStatus.BrandName) + "%");
            }
            // 规格
            if (searchMap.get(GoodsStatus.Spec) != null && !"".equals(searchMap.get(GoodsStatus.Spec))) {
                criteria.andLike(GoodsStatus.Spec, "%" + searchMap.get(GoodsStatus.Spec) + "%");
            }
            // 商品状态 1-正常，2-下架，3-删除
            if (searchMap.get(GoodsStatus.Status) != null && !"".equals(searchMap.get(GoodsStatus.Status))) {
                criteria.andEqualTo(GoodsStatus.Status, searchMap.get(GoodsStatus.Status));
            }


            // 价格（分）
            if (searchMap.get(GoodsStatus.Price) != null) {
                criteria.andEqualTo(GoodsStatus.Price, searchMap.get(GoodsStatus.Price));
            }
            // 库存数量
            if (searchMap.get(GoodsStatus.Num) != null) {
                criteria.andEqualTo(GoodsStatus.Num, searchMap.get(GoodsStatus.Num));
            }
            // 库存预警数量
            if (searchMap.get(GoodsStatus.AlertNum) != null) {
                criteria.andEqualTo(GoodsStatus.AlertNum, searchMap.get(GoodsStatus.AlertNum));
            }
            // 重量（克）
            if (searchMap.get(GoodsStatus.Weight) != null) {
                criteria.andEqualTo(GoodsStatus.Weight, searchMap.get(GoodsStatus.Weight));
            }
            // 类目ID
            if (searchMap.get(GoodsStatus.CategoryId) != null) {
                criteria.andEqualTo(GoodsStatus.CategoryId, searchMap.get(GoodsStatus.CategoryId));
            }
            // 销量
            if (searchMap.get(GoodsStatus.SaleNum) != null) {
                criteria.andEqualTo(GoodsStatus.SaleNum, searchMap.get(GoodsStatus.SaleNum));
            }
            // 评论数
            if (searchMap.get(GoodsStatus.CommentNum) != null) {
                criteria.andEqualTo(GoodsStatus.CommentNum, searchMap.get(GoodsStatus.CommentNum));
            }
        }
        return example;
    }
}
