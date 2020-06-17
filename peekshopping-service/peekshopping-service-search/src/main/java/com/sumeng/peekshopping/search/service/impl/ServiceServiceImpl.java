package com.sumeng.peekshopping.search.service.impl;


import com.alibaba.fastjson.JSON;
import com.sumeng.peekshopping.constant.MathNum;
import com.sumeng.peekshopping.search.pojo.SkuInfo;
import com.sumeng.peekshopping.search.service.SearchService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @date: 2020/6/17 19:39
 * @author: sumeng
 */
@Service
public class ServiceServiceImpl implements SearchService {


    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    /**
     * 按照查询条件进行数据查询
     *
     * @param searchMap 查询条件
     * @return 查询结果
     */
    @Override
    public Map search(Map<String, String> searchMap) {

        Map<String, Object> resultMap = new HashMap<>();

        //构建查询
        if (searchMap != null) {

            //构建查询条件封装对象
            NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
            BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();


            //按照关键字模糊查询
            if (StringUtils.isNotEmpty(searchMap.get("keywords"))) {
                boolQuery.must(QueryBuilders.matchQuery("name", searchMap.get("keywords")).operator(Operator.AND));
            }

            //按照品牌进行过滤查询
            if (StringUtils.isNotEmpty(searchMap.get("brand"))) {
                boolQuery.filter(QueryBuilders.termQuery("brandName", searchMap.get("brand")));
            }


            //按照规格进行过滤查询
            for (String key : searchMap.keySet()) {
                if (key.startsWith("spec_")) {
                    boolQuery.filter(QueryBuilders.termQuery(("specMap." + key.substring(5) + ".keyword"), searchMap.get(key)));
                }
            }


            //按照价格区间进行过滤查询
            if (StringUtils.isNotEmpty(searchMap.get("price"))) {
                String[] prices = searchMap.get("price").split("-");

                if (prices.length == 2) {
                    boolQuery.filter(QueryBuilders.rangeQuery("price").lte(prices[1]));

                }
                boolQuery.filter(QueryBuilders.rangeQuery("price").gte(prices[0]));
            }


            //按照品牌进行（分组）聚合查询
            String skuBrand = "skuBrand";
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuBrand).field("brandName"));

            //按照规格进行聚合查询
            String skuSpec = "spuSpec";
            nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms(skuSpec).field("spec.keyword"));

            nativeSearchQueryBuilder.withQuery(boolQuery);


            //开启分页查询
            //设置当前页
            String pageNum = searchMap.get("pageNum");
            if (StringUtils.isEmpty(pageNum)) {
                pageNum = MathNum.one;
            }
            //设置每页显示数量
            String pageSize = searchMap.get("pageSize");
            if (StringUtils.isEmpty(pageSize)) {
                pageSize = "30";
            }
            //设置分页
            //第一个参数:当前页 是从0开始
            //第二个参数:每页显示多少条
            nativeSearchQueryBuilder.withPageable(PageRequest.of(Integer.parseInt(pageNum) - 1, Integer.parseInt(pageSize)));


            //按照相关字段进行排序查询
            //1. 当前域    2. 当前的排序操作（升序ASC，降序DESC）
            if (StringUtils.isNotEmpty(searchMap.get("sortField")) && StringUtils.isNotEmpty(searchMap.get("sortRule"))) {
                if ("ASC".equals(searchMap.get("sortRule"))) {
                    //升序
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(searchMap.get("sortField")).order(SortOrder.ASC));
                } else {
                    //降序
                    nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort(searchMap.get("sortField")).order(SortOrder.DESC));
                }
            }


            /*
             * 设置高亮及其样式
             * Field：高亮域
             * preTags：高亮样式前缀
             * postTags：高亮样式后缀
             */
            HighlightBuilder.Field field = new HighlightBuilder.Field("name").preTags("<span style='color:red'>").postTags("</span>");
            nativeSearchQueryBuilder.withHighlightFields(field);



            /*
             * 开启查询,封装查询结果
             * 第一个参数: 条件构建对象
             * 第二个参数: 查询操作实体类
             * 第三个参数: 查询结果操作对象
             */
            AggregatedPage<SkuInfo> resultInfo = elasticsearchTemplate.queryForPage(nativeSearchQueryBuilder.build(), SkuInfo.class, new SearchResultMapper() {
                @Override
                public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {

                    //查询结果操作
                    List<T> list = new ArrayList<>();

                    //获取查询命中的结果数据
                    SearchHits hits = searchResponse.getHits();

                    if (hits != null) {
                        for (SearchHit hit : hits) {
                            //将searchHi转为skuInfo对象

                            SkuInfo skuInfo = JSON.parseObject(hit.getSourceAsString(), SkuInfo.class);

                            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                            if (highlightFields != null && highlightFields.size() > 0) {
                                //替换数据
                                skuInfo.setName(highlightFields.get("name").getFragments()[0].toString());
                            }


                            list.add((T) skuInfo);

                        }

                    }

                    return new AggregatedPageImpl<T>(list, pageable, hits.getTotalHits(), searchResponse.getAggregations());
                }

                @Override
                public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
                    return null;
                }
            });


            //封装最终的返回结果
            //总记录数
            resultMap.put("total", resultInfo.getTotalElements());
            //总页数
            resultMap.put("totalPages", resultInfo.getTotalPages());
            //数据集合
            resultMap.put("rows", resultInfo.getContent());

            //封装品牌的分组结果
            StringTerms brandTerms = (StringTerms) resultInfo.getAggregation(skuBrand);
            List<String> brandList = brandTerms.getBuckets().stream().map(StringTerms.Bucket::getKeyAsString).collect(Collectors.toList());
            resultMap.put("brandList", brandList);

            //封装规格的分组结果
            StringTerms specTerms = (StringTerms) resultInfo.getAggregation(skuSpec);
            List<String> specList = specTerms.getBuckets().stream().map(StringTerms.Bucket::getKeyAsString).collect(Collectors.toList());
            resultMap.put("specList", specList);

            //当前页
            resultMap.put("pageNum", pageNum);


            return resultMap;
        }


        return null;
    }
}
