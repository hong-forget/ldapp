package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    ClientMapper clientMapper;

    @PostMapping("/client/stalvp")
    public ReturnData staLoc(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.staLoc(param.getInteger("userId")));
    }

    @PostMapping("/client/stahis")
    public ReturnData staHistory(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.staHistory(param.getInteger("userId"),param.getInteger("days")));
    }

    @PostMapping("/client/rank")
    public ReturnData rank(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.rank(param.getInteger("userId")));
    }

    @PostMapping("/client/crcds")
    public ReturnData cRcds(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.cRcds(param.getInteger("userId"),getCdt(param)));
    }

    @PostMapping("/client/rcdcf")
    public ReturnData rcdCf(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.rcdCf(param.getInteger("cId")));
    }

    @PostMapping("/client/cshws")
    public ReturnData cShws(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.cShws(param.getInteger("userId"),getCdt(param)));
    }

    @PostMapping("/client/shwcf")
    public ReturnData shwCf(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.shwCf(param.getInteger("cId")));
    }

    @PostMapping("/client/cdeals")
    public ReturnData cDeals(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.cDeals(param.getInteger("userId"),getCdt(param)));
    }

    @PostMapping("/client/tracf")
    public ReturnData traCf(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.traCf(param.getInteger("cId")));
    }

    @PostMapping("/client/shwmf")
    public ReturnData shwMf(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.shwMf(param));
    }

    @PostMapping("/client/tramf")
    public ReturnData tramf(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.traMf(param));
    }

    @PostMapping("/client/traeds")
    public ReturnData traEds(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.traEds(param.getInteger("userId"),getCdt(param)));
    }

    @PostMapping("/client/otrcd")
    public ReturnData otrcd(@RequestBody JSONObject param){

        return new ReturnData( clientMapper.otRcd(param.getInteger("userId"),getCdt(param)));
    }

    private String getCdt(JSONObject param){
        return "%"+param.getString("search")+"%";
    }


}
