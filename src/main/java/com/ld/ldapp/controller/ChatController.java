package com.ld.ldapp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.Interceptor.AdminInterceptor;
import com.ld.ldapp.domain.ReturnData;
import com.ld.ldapp.service.ChatService;
import com.ld.ldapp.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class ChatController {


    @Value("${picPath}")
    private String picPath;

    @Autowired
    ChatService chatService;

    @PostMapping("/chat/chats")
    public ReturnData chatList(@RequestBody JSONObject param){

        return new ReturnData(chatService.getUseMsgs(param.getInteger("userId")));
    }

    @PostMapping("/chat/read")
    public ReturnData chatRead(@RequestBody JSONObject param){

        JSONArray rData= JSON.parseArray(JSON.toJSONString(chatService.readChat(param)));
        JSONArray rData2=new JSONArray();
        for (Object o : rData) {
                JSONObject obj=JSON.parseObject(JSON.toJSONString(o));
                obj.put("context",JSON.parseObject(obj.getString("context")));
                rData2.add(obj);
        }

        return new ReturnData(rData2);
    }

    @PostMapping("/chat/send")
    public ReturnData chatSend(@RequestBody JSONObject param){

        System.out.println(param);
        return new ReturnData(chatService.sendChat(param));
    }

    @PostMapping("/file/upload")
    public ReturnData chatSend(MultipartFile file) throws IOException {
        String fn= MyUtil.localVal().getInteger("userid")+file.getOriginalFilename();
        File f = new File(MyUtil.renameFile(picPath,fn));
        file.transferTo(f);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("newFileName",f.getName());
        return new ReturnData(jsonObject);
    }




}
