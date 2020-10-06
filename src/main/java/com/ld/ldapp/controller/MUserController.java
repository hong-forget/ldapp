package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.domain.User;
import com.ld.ldapp.mapper.MUserMapper;
import com.ld.ldapp.util.JwtToken;
import com.ld.ldapp.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MUserController {

    @Autowired
    MUserMapper mUserMapper;

    @PostMapping("/muser/realtor")
    public ReturnData realtor(@RequestBody(required = false) JSONObject param){

        if(param==null){
            param=new JSONObject();
        }else{
            Integer pageNum=param.getInteger("pageNum");
            Integer pageSize=param.getInteger("pageSize");
            if(pageNum==null) pageNum=0;
            if(pageSize==null) pageSize=0;
            PageHelper.startPage(pageNum,pageSize);
        }
        return new ReturnData(mUserMapper.realtor(param));
    }

    @PostMapping("/realtor/add")
    public ReturnData realtorAdd(@RequestBody JSONObject param){

        Integer index=mUserMapper.getIndex(param)+1;
        param.put("index",index);
        return new ReturnData(mUserMapper.realtorAdd(param));
    }

    @PostMapping("/realtor/find")
    public ReturnData realtorFind(@RequestBody JSONObject param){
        return new ReturnData(mUserMapper.realtorFind(param));
    }

    @PostMapping("/realtor/update")
    public ReturnData realtorUpdate(@RequestBody JSONObject param){
        Integer operat=param.getInteger("operat");
        Integer rtn=0;
        if(operat==1){
            rtn=rtn+mUserMapper.realtorUpdateStatus(param);
        }else if(operat==2){
            rtn=rtn+mUserMapper.realtorUpdateStatus(param);
        }else if(operat==3){
            Integer index=param.getInteger("index");
            param.put("orgIndex",index+1);
            rtn=rtn+mUserMapper.realtorUpdateOrgIndex(param);
            rtn=rtn+mUserMapper.realtorUpdateIndex(param);
        }else if(operat==4){
            Integer index=param.getInteger("index");
            param.put("orgIndex",index-1);
            rtn=rtn+mUserMapper.realtorUpdateOrgIndex(param);
            rtn=rtn+mUserMapper.realtorUpdateIndex(param);
        }else if(operat==5){
            rtn=rtn+mUserMapper.realtorDel(param);
        }
        return new ReturnData(rtn);
    }

    @PostMapping("/agent/set")
    public ReturnData agentSet(@RequestBody JSONObject param){


        mUserMapper.agentSet(param);


        return new ReturnData(mUserMapper.agentSetRole(param));
    }

    @PostMapping("/agent/list")
    public ReturnData agentList(@RequestBody JSONObject param){
        MyUtil.setPage(param);


        return new ReturnData(mUserMapper.agentList(param));
    }

    @PostMapping("/muser/login")
    public ReturnData login(@RequestBody JSONObject param){

        if(mUserMapper.login(param)>0){
            String token= JwtToken.generateToken(param.toJSONString());
            param.put("token",token);
        }

        return new ReturnData(param);
    }
    @PostMapping("/muser/resetpwd")
    public ReturnData resetpwd(@RequestBody JSONObject param){

        if(mUserMapper.login(param)>0){
            return new ReturnData(mUserMapper.reSetPwd(param));
        }
        return new ReturnData();

    }
}
