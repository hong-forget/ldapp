package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.PlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {

    @Autowired
    PlanMapper planMapper;

    @PostMapping("/plan/find")
    public ReturnData findByBuildingId(@RequestBody JSONObject param){

        return new ReturnData(planMapper.findByBuildingId(param));
    }

}
