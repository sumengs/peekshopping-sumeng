package com.sumeng.peekshopping.search.controller;

import com.sumeng.peekshopping.entity.Result;
import com.sumeng.peekshopping.entity.StatusCode;
import com.sumeng.peekshopping.search.service.EsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @date: 2020/6/16 15:50
 * @author: sumeng
 */

@RestController
@RequestMapping("/manager")
public class EsManagerController {

    @Autowired
    private EsManagerService esManagerService;


    /**
     * 创建索引库结构
     *
     * @return result
     */
    @GetMapping("/create")
    public Result<String> create() {
        esManagerService.createIndexAndMapping();
        return new Result<>(true, StatusCode.OK, "创索引库结构成功", "");
    }


    /**
     * 导入全部数据
     *
     * @return result
     */
    @GetMapping("/importAll")
    public Result<String> importAll() {
        esManagerService.importAll();
        return new Result<>(true, StatusCode.OK, "导入全部数据成功", "");
    }


}
