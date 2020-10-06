package com.ld.ldapp.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.manager.FlagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlagController {

    @Autowired
    FlagMapper flagMapper;

    @PostMapping("/flag/set")
    public ReturnData setFlag(@RequestBody JSONObject param){

        return new ReturnData(flagMapper.flagSet(param));
    }

    @PostMapping("/flag/find")
    public ReturnData setFind(@RequestBody JSONObject param){

        return new ReturnData(flagMapper.flagSet(param));
    }
}
