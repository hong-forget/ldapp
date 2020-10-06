package com.ld.ldapp.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.Banner;
import com.ld.ldapp.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BannerService {

    @Autowired
    BannerMapper bannerMapper;

    public List<Banner> getList(Integer pagesize){
        PageHelper.startPage(1, pagesize);
        return bannerMapper.findAll();
    }

    public Banner getOne(Integer id){
        return bannerMapper.findOneById(id);
    }

    public List<Map> list(JSONObject param) {
        if(param==null) param=new JSONObject();
        Integer pageNum=param.getInteger("pageNum");
        Integer pageSize=param.getInteger("pageSize");
        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);
        return bannerMapper.list(param);
    }

    public Integer update(JSONObject param) {
        return bannerMapper.update(param);
    }

    public Integer add(JSONObject param) {
        return bannerMapper.add(param);
    }
}
