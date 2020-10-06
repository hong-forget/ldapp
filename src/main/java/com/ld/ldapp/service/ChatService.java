package com.ld.ldapp.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.ld.ldapp.domain.Chat;
import com.ld.ldapp.mapper.ChatMapper;
import com.ld.ldapp.config.socket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatMapper chatMapper;

    @Autowired
    UserService userService;

    public Chat getChat(Integer id){
        Chat list=chatMapper.findById(id);
        return list;
    }



    public Integer unReadTotal(Integer userId){
        return chatMapper.unReadTotal(userId);
    }

    public List<HashMap> readChat(JSONObject param){


        Integer fromId=param.getInteger("fromId");
        Integer toId=param.getInteger("toId");
        Integer pageNum=param.getInteger("pagenum");
        Integer pageSize=param.getInteger("pagesize");

        if(pageNum==null) pageNum=0;
        if(pageSize==null) pageSize=0;
        PageHelper.startPage(pageNum,pageSize);

        chatMapper.updateChat(fromId,toId);
        return chatMapper.readChat(fromId,toId);
    }

//    public Integer sendChat(Integer fromId,Integer toId,String context){
//        return chatMapper.addChat(fromId,toId,context,null);
//    }

    public List<HashMap> getUseMsgs(Integer id){
        return chatMapper.getUseMsgs(id);
    }

    public Integer sendChat(JSONObject param) {


        param.put("context",param.getJSONObject("context").toJSONString());
        Integer i=chatMapper.addChat(param);
        Chat chat=chatMapper.findById(param.getInteger("id"));
        String massage =  JSONObject.toJSONString(chat);
        JSONObject msg=JSONObject.parseObject(massage);
        msg.put("context",JSONObject.parseObject(msg.getString("context")));
        Integer newMsgtotal =userService.useMsgs(param.getInteger("toId"));
        msg.put("newMsgtotal",newMsgtotal);
        WebSocket.sendWholeAsyncMessage( msg.toJSONString(),param.getString("toId"));

        return i;
    }
}
