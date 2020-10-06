package com.ld.ldapp.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class BuildingParam {

    private Integer lowPrice;
    private Integer highPrice;
    private Integer area;
    private Integer status;
    private Integer type;
    private Integer sort;
    private String name;

    public BuildingParam(JSONObject param) {
        JSONObject search=param.getJSONObject("search");
        if(search!=null){
            this.area=search.getInteger("area");
            this.status=search.getInteger("status");
            this.type=search.getInteger("type");
            this.sort=search.getInteger("sort");
            String prices=search.getString("price");
            if(prices!=null){
                String[] priceAry=prices.split("-");
                this.lowPrice=Integer.parseInt(priceAry[0]);
                this.highPrice=Integer.parseInt(priceAry[1]);
            }
        }

        if(param.getString("name")!=null)
        this.name="%"+param.getString("name")+"%";

    }
}
