package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.ClientMapper;
import com.ld.ldapp.mapper.NicheMapper;
import com.ld.ldapp.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
public class NicheController {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private NicheMapper nicheMapper;

    @PostMapping("/niche/statistics")
    public ReturnData statistics(@RequestBody JSONObject param){

        List<HashMap> list=clientMapper.getStatistics(param.getInteger("userId"));

       JSONArray array= JSON.parseArray(JSON.toJSONString(list));

        JSONObject cycle1=new JSONObject();
        JSONObject cycle2=new JSONObject();
        JSONObject cycle3=new JSONObject();
        JSONObject cycle4=new JSONObject();
        for (Object o : array) {
            JSONObject obj=JSON.parseObject(JSON.toJSONString(o));
            if(obj.getInteger("cycle")==1){
                cycle1.put(obj.getString("type"),obj.getInteger("total"));
            }else if(obj.getInteger("cycle")==2){
                cycle2.put(obj.getString("type"),obj.getInteger("total"));
            }else if(obj.getInteger("cycle")==3){
                cycle3.put(obj.getString("type"),obj.getInteger("total"));
            }else if(obj.getInteger("cycle")==4){
                cycle4.put(obj.getString("type"),obj.getInteger("total"));
            }
        }

        JSONObject array2=new JSONObject();
        array2.put("1",cycle1);
        array2.put("2",cycle2);
        array2.put("3",cycle3);
        array2.put("4",cycle4);
        return new ReturnData(array2);
    }

    @PostMapping("/niche/record")
    public ReturnData record(@RequestBody JSONObject param){

        return new ReturnData(clientMapper.record(param));
    }

    @PostMapping("/niche/recordlist")
    public ReturnData recordlist(@RequestBody JSONObject param){


        return new ReturnData(clientMapper.getRecordList(param));
    }



    @PostMapping("/niche/addopt")
    public ReturnData addopt(@RequestBody JSONObject param){



        return new ReturnData(nicheMapper.addopt(param));
    }

    @PostMapping("/niche/getopt")
    public ReturnData getOpt(@RequestBody JSONObject param){



        Integer pageNum=param.getInteger("pagenum");
        Integer pageSize=param.getInteger("pagesize");

        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);

        return new ReturnData(nicheMapper.getOpt(param));
    }

    @PostMapping("/niche/getopts")
    public ReturnData getOpts(@RequestBody JSONObject param){



        Integer pageNum=param.getInteger("pagenum");
        Integer pageSize=param.getInteger("pagesize");

        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);
        return new ReturnData(nicheMapper.getOpts(param));
    }



    @PostMapping("/niche/clients")
    public ReturnData clients(String userid,String search){
        if(userid==null||search==null){
            return new ReturnData();
        }
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id","0");
        jsonObject.put("clientname","张伟");
        jsonObject.put("clientphone","15178878888");
        jsonObject.put("buildingid","1");

        jsonObject.put("followtime", MyUtil.dataTime(new Date()));
        jsonObject.put("intention","A");
        jsonObject.put("star","");
        jsonArray.add(jsonObject);
        JSONObject jsonObject1=new JSONObject();
        jsonObject1.put("id","0");
        jsonObject1.put("clientname","张伟");
        jsonObject1.put("clientphone","15178878888");
        jsonObject1.put("buildingid","1");

        jsonObject1.put("followtime", MyUtil.dataTime(new Date()));
        jsonObject1.put("intention","A");
        jsonObject1.put("star","");
        jsonArray.add(jsonObject1);


        return new ReturnData(jsonArray);
    }



}
