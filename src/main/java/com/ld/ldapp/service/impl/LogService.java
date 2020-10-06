package com.ld.ldapp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    LogMapper logMapper;

    public void addLog(JSONObject info) {
        String touristKey=info.getString("tourist_key");
        if(touristKey==null||touristKey.isEmpty()){
            info.put("uType",1);
        }else{
            info.put("uType",0);
        }
        logMapper.addReqestLog(info);

    }

    public JSONObject statistics() {
        return logMapper.statistics();
    }
}
