package com.ld.ldapp.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.manager.AgencyMapper;
import com.ld.ldapp.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class AgencyController {

    @Autowired
    AgencyMapper agencyMapper;

    @PostMapping("/agency/list")
    public ReturnData list(@RequestBody(required = false) JSONObject param){
        MyUtil.setPage(param);
        return new ReturnData(agencyMapper.list(param));
    }

    @PostMapping("/agency/mdify")
    public ReturnData mdify(@RequestBody(required = false) JSONObject param){
        if(param.getInteger("id")==null){
            return new ReturnData(agencyMapper.add(param));

        }else{
            return new ReturnData(agencyMapper.mdify(param));
        }
    }

    @PostMapping("/agency/read")
    public ReturnData read(@RequestBody(required = false) JSONObject param){

        return new ReturnData(agencyMapper.read(param));

    }

    @PostMapping("/agency/realtor")
    public ReturnData realtor(@RequestBody(required = false) JSONObject param){

        return new ReturnData(agencyMapper.realtor(param));

    }

    @PostMapping("/agency/statistic")
    public ReturnData statistic(@RequestBody(required = false) JSONObject param){

        return new ReturnData(agencyMapper.statistic(param));

    }

}
