package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.domain.User;
import com.ld.ldapp.service.UserService;
import com.ld.ldapp.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {




    @Autowired
    private UserService userService;


    @PostMapping("/user/info")
    public ReturnData info(@RequestBody JSONObject param){
        return new ReturnData(userService.getUserById(param.getInteger("userId")));
    }

    @PostMapping("/user/login")
    public ReturnData login(@RequestBody JSONObject param){


        User user=userService.login(param.getString("wxid"),param.getInteger("referrer_id"),param);


//        User user=new User();
//        user.setId(6);
//        user.setName("黄牛");
        if(user==null) return new ReturnData();
        JSONObject jsonObject2=user.loginReturn();

        String token=JwtToken.generateToken(jsonObject2.toJSONString());
        if(user.getReferrerId()!=null){
            User reUser=userService.getUserById(user.getReferrerId());
            jsonObject2.put("referrer",reUser.eferrerReturn());

        }

        jsonObject2.put("token",token);
        return new ReturnData(jsonObject2);
    }

    @PostMapping("/user/tourist")
    public ReturnData tourist(@RequestBody JSONObject param){

        return new ReturnData(userService.tourist(param.getString("touristKey")));
    }

    @PostMapping("/user/bebroker")
    public ReturnData bebroker(@RequestBody JSONObject param, @RequestHeader String authorization){
        if(param==null){
            return new ReturnData();
        }

        User user=userService.beBroker(param);
        if(user==null){
            return new ReturnData("门店代码错误");
        }
        return new ReturnData(user.loginReturn());
    }

    @PostMapping("/user/lvp")
    public ReturnData lvp(@RequestBody JSONObject param, @RequestHeader String authorization){
        if(param==null){
            return new ReturnData();
        }

        User user=userService.beLvp(param);
        if(user==null){
            return new ReturnData("门店代码错误");
        }
        return new ReturnData(user.loginReturn());
    }

    @PostMapping("/user/lvpbind")
    public ReturnData lvpBind(@RequestBody JSONObject param, @RequestHeader String authorization){
        if(param==null){
            return new ReturnData();
        }

        Integer rst=userService.lvpBind(param);


        if(rst==0){
            return new ReturnData();
        }


        return new ReturnData(rst);
    }

    @GetMapping("/error")
    public ReturnData error(Integer id){
        return new ReturnData("没有权限");
    }

    @PostMapping("/user/dcy")
    public ReturnData dcy(@RequestBody JSONObject param){

        return new ReturnData( userService.dcy(param));
    }




}
