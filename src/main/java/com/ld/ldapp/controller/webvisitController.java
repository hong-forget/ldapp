package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.service.impl.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class webvisitController {

    @Autowired
    LogService logService;


    @PostMapping("webvisit/statistics")
    public ReturnData statistics(){




        JSONObject rtn=logService.statistics();

        return new ReturnData(rtn);
    }
}
