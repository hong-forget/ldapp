package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.AreaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaController {

    @Autowired
    AreaMapper areaMapper;

    @PostMapping("/area/add")
    public ReturnData add(@RequestBody JSONObject param){



        return new ReturnData(areaMapper.add(param));
    }

    @PostMapping("/area/find")
    public ReturnData findChildren(@RequestBody JSONObject param){

        return new ReturnData(areaMapper.findChildren(param.getInteger("parentId")));
    }

    @PostMapping("/area/del")
    public ReturnData del(@RequestBody JSONObject param){

        return new ReturnData(areaMapper.del(param.getInteger("id")));
    }
}
