package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.AdvanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvanceController {

    @Autowired
    AdvanceMapper advanceMapper;

    @PostMapping("/advance/statistics")
    public ReturnData advSta(){

        JSONObject rtn=new JSONObject();
        rtn.put("total",advanceMapper.advStatistics());
        return new ReturnData(rtn);
    }

    @PostMapping("/advance/list")
    public ReturnData advList(@RequestBody(required=false) JSONObject param){

        if(param==null) param=new JSONObject();
        Integer pageNum=param.getInteger("pageNum");
        Integer pageSize=param.getInteger("pageSize");
        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);
        return new ReturnData(advanceMapper.advList(param));
    }

    @PostMapping("/advance/update")
    public ReturnData updateStatus(@RequestBody JSONObject param){

        Integer i=0;
        Integer status=param.getInteger("status");
        for (Object oid : param.getJSONArray("ids")) {
            Integer id=(Integer) oid;
            i=i+advanceMapper.updateStatus(status,id);
        }
        return new ReturnData(i);
    }

    @PostMapping("/advance/del")
    public ReturnData delStatus(@RequestBody JSONObject param){

        Integer i=0;
        for (Object oid : param.getJSONArray("ids")) {
            Integer id=(Integer) oid;
            i=i+advanceMapper.delStatus(id);
        }
        return new ReturnData(i);
    }
}
