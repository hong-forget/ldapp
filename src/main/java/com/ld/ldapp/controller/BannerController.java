package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BannerController {


    @Autowired
    BannerService bannerService;

    @PostMapping("/banner/bannerlist")
    public ReturnData bannerlist(@RequestBody JSONObject param){




        return new ReturnData(bannerService.getList(param.getInteger("pagesize")));
    }

    @PostMapping("/banner/list")
    public ReturnData list(@RequestBody JSONObject param){




        return new ReturnData(bannerService.list(param));
    }

    @PostMapping("/banner/add")
    public ReturnData add(@RequestBody JSONObject param){


        if(param.getInteger("id")==null){
            return new ReturnData(bannerService.add(param));
        }else{
            return new ReturnData(bannerService.update(param));

        }


    }


    @PostMapping("/banner/banner")
    public ReturnData banner(@RequestBody JSONObject param){




        return new ReturnData(bannerService.getOne(param.getInteger("id")));
    }


}
