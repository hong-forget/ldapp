package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.CusMapper;
import com.ld.ldapp.mapper.NicheMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class CusController {

    @Autowired
    private CusMapper cusMapper;

    @Autowired
    private NicheMapper nicheMapper;

    @PostMapping("/niche/addcus")
    public ReturnData statistics(@RequestBody JSONObject param){

        Integer i=cusMapper.addCus(param);
        String bidStr=param.getString("careBuildingId");
        if(!bidStr.isEmpty()){
            JSONObject temp=new JSONObject();
            temp.put("cId",param.get("id"));
            for (String s : bidStr.split(",")) {
                temp.put("bId",s);
                cusMapper.addRCusBdg(temp);
            }
        }

        return new ReturnData(i);
    }

    @PostMapping("/niche/cuslist")
    public ReturnData cusList(@RequestBody JSONObject param){
        String search=param.getString("search");
        if(search!=null) param.put("search","%"+search+"%");
        List<HashMap> i=cusMapper.getCusList(param);

        return new ReturnData(i);
    }

    @PostMapping("/niche/cusdtl")
    public ReturnData cusdtl(@RequestBody JSONObject param){

        JSONObject i=cusMapper.getDtl(param.getInteger("cusId"));

        List<Map> arr=cusMapper.getCusTuhs(param.getInteger("cusId"));
        i.put("tuhs",arr);

        return new ReturnData(i);
    }

    @PostMapping("/niche/custuh")
    public ReturnData custuh(@RequestBody JSONObject param){

        Integer i=cusMapper.addCusTuh(param);
        Integer j=cusMapper.updateCus(param);
        return new ReturnData(i);
    }

    @PostMapping("/niche/cusstar")
    public ReturnData cusstar(@RequestBody JSONObject param){

        Integer i=cusMapper.cusStar(param);

        return new ReturnData(i);
    }

}
