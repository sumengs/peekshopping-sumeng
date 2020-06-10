package com.sumeng.peekshopping.goods.service.impl;

import com.alibaba.fastjson.JSON;
import com.sumeng.peekshopping.constant.MathNum;
import com.sumeng.peekshopping.goods.dao.BrandMapper;
import com.sumeng.peekshopping.goods.dao.CategoryMapper;
import com.sumeng.peekshopping.goods.dao.SkuMapper;
import com.sumeng.peekshopping.goods.dao.SpuMapper;
import com.sumeng.peekshopping.goods.pojo.*;
import com.sumeng.peekshopping.goods.service.SpuService;
import com.sumeng.peekshopping.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @date: 2020/6/10 9:04
 * @author: sumeng
 */
@Service
public class SpuServiceImpl implements SpuService {


    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private SkuMapper skuMapper;

    /**
     * 保存商品
     *
     * @param goods 商品组合实体类
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(Goods goods) {
        Spu spu = goods.getSpu();
        long spuId = idWorker.nextId();
        spu.setId(String.valueOf(spuId));
        //未删除
        spu.setIsDelete("0");
        //未上架
        spu.setIsMarketable("0");
        //未审核
        spu.setStatus("0");

        //保存spu到数据库
        spuMapper.insertSelective(spu);

        //将sku集合保存到数据库
        saveSkuList(goods);
    }


    /**
     * 根据Id查询商品详情
     *
     * @param id 商品id
     * @return id
     */
    @Override
    public Goods findGoodsById(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);

        //查询sku
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("spuId", id);
        List<Sku> skuList = skuMapper.selectByExample(example);

        //封装返回结果
        Goods goods = new Goods();
        goods.setSpu(spu);
        goods.setSkuList(skuList);
        return goods;
    }


    /**
     * 更新商品详情
     *
     * @param goods 商品数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Goods goods) {
        Spu spu = goods.getSpu();
        spuMapper.updateByPrimaryKey(spu);

        //删除原sku列表
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("spuId", spu.getId());

        skuMapper.deleteByExample(example);

        //保存sku列表
        saveSkuList(goods);

    }


    /**
     * 商品审核
     *
     * @param id spuID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void audit(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);

        //判断商品是否存在
        if (spu == null) {
            throw new RuntimeException("当前商品不存在");
        }
        /*
         * 不能是逻辑删除的数据
         * 数据必须是未审核的状态
         */
        //判断商品是否处于删除状态
        if (MathNum.one.equals(spu.getIsDelete())) {
            throw new RuntimeException("当前商品处于删除状态");
        }

        //修改为上架状态
        spu.setStatus(MathNum.one);
        spu.setIsMarketable(MathNum.one);

        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 商品下架
     *
     * @param id spuID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void pull(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);

        if (spu == null) {
            throw new RuntimeException("当前商品不存在");
        }

        //判断当前商品是否处于删除状态
        if (MathNum.one.equals(spu.getIsDelete())) {
            throw new RuntimeException("当前商品处于删除状态");
        }

        //修改为下架状态
        spu.setIsMarketable(MathNum.zero);
        spuMapper.updateByPrimaryKeySelective(spu);
    }


    /**
     * 商品上架
     *
     * @param id spuID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void post(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);

        //判商品是否存在
        if (spu == null) {
            throw new RuntimeException("当前商品不存在");
        }

        //判断商品是否被删除
        if (spu.getIsDelete().equals(MathNum.one)) {
            throw new RuntimeException("当前商品已经被删除");
        }

        //判断商品是否通过审核
        if (!spu.getStatus().equals(MathNum.one)) {
            throw new RuntimeException("未通过审核的商品不能上架！");
        }

        //上架商品
        spu.setIsMarketable(MathNum.one);
        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 逻辑删除商品
     *
     * @param id supId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);

        //判断spu是否存在
        if (spu == null) {
            throw new RuntimeException("需要删除的商品不存在");
        }

        //判断商品是否下架
        if (spu.getIsMarketable().equals(MathNum.one)) {
            throw new RuntimeException("必须先下架再删除！");
        }
        //删除
        spu.setIsDelete(MathNum.one);
        //未审核
        spu.setStatus(MathNum.zero);

        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 商品还原
     *
     * @param id supId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void restore(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);

        if (spu == null) {
            throw new RuntimeException("商品不存在");
        }

        //检查是否是呗删除的商品
        if (!spu.getIsDelete().equals(MathNum.one)) {
            throw new RuntimeException("该商品未被删除");
        }
        //未被删除
        spu.setIsDelete(MathNum.zero);
        //未审核状态
        spu.setStatus(MathNum.zero);

        spuMapper.updateByPrimaryKeySelective(spu);
    }

    /**
     * 物理删除
     *
     * @param id spu ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void realDelete(String id) {
        Spu spu = spuMapper.selectByPrimaryKey(id);
        if (spu == null) {
            throw new RuntimeException("商品不存在");
        }

        if (!spu.getIsDelete().equals(MathNum.one)) {
            throw new RuntimeException("此商品未被移入回收站");
        }

        spuMapper.deleteByPrimaryKey(id);


        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("spuId", id);
        skuMapper.deleteByExample(example);
    }

    /**
     * 保存sku列表
     *
     * @param goods 商品组合实体类
     */
    private void saveSkuList(Goods goods) {
        //获取spu对象
        Spu spu = goods.getSpu();

        //当前日期
        Date date = new Date();

        //获取品牌对象
        Brand brand = brandMapper.selectByPrimaryKey(spu.getBrandId());

        //获取分类对象
        Category category = categoryMapper.selectByPrimaryKey(spu.getCategory3Id());

        //获取sku集合对象
        List<Sku> skuList = goods.getSkuList();

        if (skuList != null) {
            for (Sku sku : skuList) {
                //设置sku的主键ID
                sku.setId(String.valueOf(idWorker.nextId()));

                //设置sku的规格
                if (sku.getSpec() == null || "".equals(sku.getSpec())) {
                    sku.setSpec("{}");
                }

                //设置sku名称 商品名称加规格
                StringBuilder name = new StringBuilder(spu.getName());

                //将规格json字符串转为Map
                Map specMap = JSON.parseObject(sku.getSpec(), Map.class);
                if (specMap != null && specMap.size() > 0) {
                    for (Object value : specMap.values()) {
                        name.append(" ").append(value);
                    }
                }

                sku.setName(String.valueOf(name));
                sku.setSpuId(spu.getId());
                sku.setCreateTime(date);
                sku.setUpdateTime(date);
                sku.setCategoryId(category.getId());
                sku.setCategoryName(category.getName());
                sku.setBrandName(brand.getName());

                skuMapper.insertSelective(sku);
            }

        }

    }
}
