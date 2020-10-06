package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.HouseTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseTypeController {

    @Autowired
    HouseTypeMapper houseTypeMapper;

    @PostMapping("housetype/add")
    public ReturnData houseTypeAdd(@RequestBody JSONObject param){
        if(param.getInteger("id")==null){

            return new ReturnData(houseTypeMapper.add(param));

        }else{

            return new ReturnData(houseTypeMapper.udpate(param));

        }
    }

    @PostMapping("housetype/del")
    public ReturnData houseTypeDel(@RequestBody JSONObject param){
        return new ReturnData(houseTypeMapper.del(param));
    }

    @PostMapping("housetype/find")
    public ReturnData houseTypeFind(@RequestBody JSONObject param){
        return new ReturnData(houseTypeMapper.find(param));
    }
}
