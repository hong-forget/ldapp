package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.*;
import com.ld.ldapp.service.BuildingService;
import com.ld.ldapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingController {


    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;


    @PostMapping("/properties/addmapbub")
    public ReturnData addBub(@RequestBody JSONObject param){



        return new ReturnData(buildingService.addBub(param));
    }

    @PostMapping("/properties/addnews")
    public ReturnData addNews(@RequestBody JSONObject param){



        return new ReturnData(buildingService.addNews(param));
    }
    @PostMapping("/properties/getnews")
    public ReturnData getNews(@RequestBody JSONObject param){



        return new ReturnData(buildingService.getNews(param));
    }

    @PostMapping("/properties/advance")
    public ReturnData advance(@RequestBody JSONObject param){



        return new ReturnData( buildingService.addAdvance(param));
    }

    @PostMapping("/properties/countadv")
    public ReturnData countAdv(@RequestBody JSONObject param){


        param.put("total",buildingService.countAdv(param));
        return new ReturnData(param);
    }

    @PostMapping("/properties/buildingdetial")
    public ReturnData buildingdetial(@RequestBody JSONObject param){



        Building building= buildingService.getBuildinngById(param.getInteger("id"));
        JSONObject jsonObject=(JSONObject)JSONObject.toJSON(building);
        String[] tags=jsonObject.get("tag").toString().split(",");
        jsonObject.put("tag",tags);
        Integer brokerid=jsonObject.getInteger("brokerid");
        jsonObject.remove("brokerid");
        User user=userService.getUserById(brokerid);
        jsonObject.put("broker",user.eferrerReturn());

        List<User> brokers=userService.buildingBroker(building.getId());
        JSONArray brokersJson= new JSONArray();
        for (User user1 : brokers) {
            brokersJson.add(user1.eferrerReturn());
        }
        jsonObject.put("brokers",brokersJson);

        Property property=buildingService.getProperty(building.getId());
        jsonObject.put("property",property);


        List<HouseStyle> hsList=buildingService.getHSList(building.getId());
        jsonObject.put("houseStyle",hsList);



        return new ReturnData(jsonObject);
    }

    @PostMapping("/properties/buildinglist")
    public ReturnData buildinglist(@RequestBody JSONObject param){
        BuildingParam buildingParam=new BuildingParam(param);
        List<Building> list= buildingService.findList(param.getInteger("pagenum"),param.getInteger("pagesize"),buildingParam);
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(list));
        for (Object object : array) {

            JSONObject jsonObject=(JSONObject)object;
            if(jsonObject.get("tag")!=null){
                String[] tags=jsonObject.get("tag").toString().split(",");
                //JSONArray array1= JSONArray.parseArray(JSON.toJSONString(tags));
                jsonObject.put("tag",tags);
            }

            Integer brokerid=jsonObject.getInteger("brokerid");
            if(brokerid!=null){
                jsonObject.remove("brokerid");
                User user=userService.getUserById(brokerid);
                jsonObject.put("broker",user.eferrerReturn());
            }
        }

        return new ReturnData(array);
    }

    @PostMapping("/properties/dic")
    public ReturnData dic(){
        return new ReturnData(buildingService.getBuildinngNames());
    }

    @PostMapping("/properties/mapbubs")
    public ReturnData mapBubs(@RequestBody JSONObject param){
        return new ReturnData(buildingService.getBubs(param));
    }

}
