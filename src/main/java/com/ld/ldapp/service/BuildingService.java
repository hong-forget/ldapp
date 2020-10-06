package com.ld.ldapp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.Building;
import com.ld.ldapp.domain.BuildingParam;
import com.ld.ldapp.domain.HouseStyle;
import com.ld.ldapp.domain.Property;
import com.ld.ldapp.mapper.BuildingMapper;
import com.ld.ldapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingService {



    @Autowired
    BuildingMapper buildingMapper;

    @Autowired
    UserMapper userMapper;


    public Integer addAdvance(JSONObject param){
        Integer i=buildingMapper.addAdvance(param);
        return i;
    }

    public Integer countAdv(JSONObject param){
        Integer i=buildingMapper.countAdv(param);
        return i;
    }


    public List<Building> findList(Integer pageSize, Integer pageNum, BuildingParam buildingParam){
        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);
        List<Building> list=buildingMapper.findList1(buildingParam);
        return list;
    }

    public List<HouseStyle> getHSList(Integer buildingId){
        List<HouseStyle> list=buildingMapper.getHouseStyleList(buildingId);
        return list;
    }

    public Property getProperty(Integer buildingId){
        Property property=buildingMapper.getPropertyByBuildingId(buildingId);
        return property;
    }

    public Building getBuildinngById(Integer id){
        Building building=buildingMapper.findOneById(id);
        return building;
    }

    public JSONArray getBuildinngNames(){
        JSONArray arr= JSON.parseArray(JSON.toJSONString(buildingMapper.getNames()));
        return arr;
    }


    public Integer addBub(JSONObject param) {
        Integer i= buildingMapper.addBub(param);
        Integer j=buildingMapper.addRBub(param);
        return i;

    }

    public JSONArray getBubs(JSONObject param){
        JSONArray arr= JSON.parseArray(JSON.toJSONString(buildingMapper.getBubs(param)));
        return arr;
    }

    public Integer bindLvp(Integer loctId, Integer bId) {

        return buildingMapper.bindLvp(loctId,bId);
    }

    public List<JSONObject> getNews(JSONObject param) {
        Integer pageNum=param.getInteger("pageNum");
        Integer pageSize=param.getInteger("pageSize");
        String cdt=param.getString("cdt");
        if(cdt!=null){
            param.put("cdt","%"+cdt+"%");
        }
        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);
        List<JSONObject>  newsList=buildingMapper.getNews(param);
        return newsList;
    }

    public JSONObject addNews(JSONObject param) {
        if(param.getInteger("id")==null){
            buildingMapper.addNews(param);
        }else{
            buildingMapper.updateNew(param);
        }
        return buildingMapper.findOneNewsById(param.getInteger("id"));
    }
}
