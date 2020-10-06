package com.ld.ldapp.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.manager.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class NewsController {


    @Autowired
    NewsMapper newsMapper;

    //未使用
    @PostMapping("/news/find")
    public ReturnData newsFind(@RequestBody(required = false)JSONObject param){


        return new ReturnData(newsMapper.find(param));
    }
}
