package com.ld.ldapp.controller.manager;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.manager.DiscountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiscountCountroller {

    @Autowired
    private DiscountMapper discountMapper;


    @PostMapping("/discount/add")
    public ReturnData add(@RequestBody JSONObject param){
        if(param.getInteger("id")!=null){
            discountMapper.updateBuilding(param);
            return new ReturnData(discountMapper.update(param));
        }else{
            discountMapper.updateBuilding(param);
            return new ReturnData(discountMapper.add(param));
        }
    }

    @PostMapping("/discount/find")
    public ReturnData update(@RequestBody JSONObject param){

        return new ReturnData(discountMapper.find(param));
    }
}
