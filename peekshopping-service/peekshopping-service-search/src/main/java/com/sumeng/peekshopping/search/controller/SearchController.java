package com.sumeng.peekshopping.search.controller;

import com.sumeng.peekshopping.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @date: 2020/6/17 19:56
 * @author: sumeng
 */

@RestController
@RequestMapping("/search")
public class SearchController {


    @Autowired
    private SearchService searchService;


    @GetMapping
    private Map search(@RequestParam Map<String, String> searchMap) {

        return searchService.search(searchMap);
    }
}
