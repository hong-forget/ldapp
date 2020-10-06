package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.BuildManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildMangerControler {

    @Autowired
    BuildManagerMapper buildManagerMapper;

    @PostMapping("/build/total")
    public ReturnData total(){
        return new ReturnData(buildManagerMapper.total());
    }

    @PostMapping("/build/list")
    public ReturnData list(@RequestBody(required = false) JSONObject param){
        if(param==null) param=new JSONObject();
        Integer pageNum=param.getInteger("pageNum");
        Integer pageSize=param.getInteger("pageSize");
        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);
        return new ReturnData(buildManagerMapper.list(param));
    }

    @PostMapping("/build/add")
    public ReturnData add(@RequestBody(required = false) JSONObject param){
        if(param.getInteger("id")==null){
            buildManagerMapper.add(param);
            JSONObject rtn=new JSONObject();
            rtn.put("id",param.getInteger("id"));
            return new ReturnData(rtn);
        }else{


            return new ReturnData(buildManagerMapper.update(param));
        }

    }

    @PostMapping("/build/find")
    public ReturnData find(@RequestBody JSONObject param){

        return new ReturnData(buildManagerMapper.find(param));
    }


    @PostMapping("/build/planadd")
    public ReturnData planAdd(@RequestBody(required = false) JSONObject param){

        if(param.getInteger("id")==null){

            return new ReturnData(buildManagerMapper.planAdd(param));
        }else{
            return new ReturnData(buildManagerMapper.planUpdate(param));
        }
    }

    @PostMapping("/build/introduceadd")
    public ReturnData introduceAdd(@RequestBody(required = false) JSONObject param){
        if(param.getInteger("id")==null){
            return new ReturnData(buildManagerMapper.introduceAdd(param));
        }else{

            return new ReturnData(buildManagerMapper.introduceUpdate(param));

        }


    }

    @PostMapping("/introduce/find")
    public ReturnData introduceFind(@RequestBody(required = false) JSONObject param){
            return new ReturnData(buildManagerMapper.findIntroduceByBuildingId(param));
    }

    @PostMapping("/license/presaleadd")
    public ReturnData preSaleLicenseAdd(@RequestBody JSONObject param){
        return new ReturnData(buildManagerMapper.preSaleLiceseAdd(param));
    }

    @PostMapping("/license/presaledel")
    public ReturnData preSaleLicenseDel(@RequestBody JSONObject param){
        return new ReturnData(buildManagerMapper.preSaleLiceseDel(param));
    }

    @PostMapping("/license/presalefind")
    public ReturnData preSaleLicenseFind(@RequestBody JSONObject param){
        return new ReturnData(buildManagerMapper.preSaleLiceseFind(param));
    }

}
