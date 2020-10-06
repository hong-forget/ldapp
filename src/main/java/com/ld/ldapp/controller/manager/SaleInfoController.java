package com.ld.ldapp.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.manager.SaleInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleInfoController {

    @Autowired
    SaleInfoMapper saleInfoMapper;

    @PostMapping("/saleinfo/modify")
    public ReturnData modify(@RequestBody JSONObject param){

        if(param.getInteger("id")==null){
            return new ReturnData(saleInfoMapper.add(param));
        }else {
            return new ReturnData(saleInfoMapper.modify(param));
        }

    }

    @PostMapping("/saleinfo/list")
    public ReturnData list(@RequestBody JSONObject param){


        return new ReturnData(saleInfoMapper.list(param));
    }

}
