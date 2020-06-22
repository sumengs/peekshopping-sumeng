package com.sumeng.peekshopping.search.controller;

import com.sumeng.peekshopping.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @date: 2020/6/17 19:56
 * @author: sumeng
 */

@Controller
@RequestMapping("/search")
public class SearchController {


    @Autowired
    private SearchService searchService;


    @GetMapping("/list")
    public String list(@RequestParam Map<String, String> searchMap, Model model) {


        //获取查询结果
        Map resultMap = searchService.search(searchMap);
        model.addAttribute("result",resultMap);
        model.addAttribute("searchMap",searchMap);


        return "search";


    }


    @GetMapping
    @ResponseBody
    public Map search(@RequestParam Map<String, String> searchMap) {

        return searchService.search(searchMap);
    }
}
