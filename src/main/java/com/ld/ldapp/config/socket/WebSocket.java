package com.ld.ldapp.config.socket;

import com.alibaba.fastjson.JSONObject;
import com.ld.ldapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint(value = "/ws/{uid}",configurator = WebSocketConfig.class)
public class WebSocket {
    private Session session;

    public static UserService userService;
    public static ConcurrentHashMap<String,WebSocket> webSocketSet=new ConcurrentHashMap<>();
    private String uid;

    @OnOpen
    public void onOpen(@PathParam("uid") String uid,Session session) {
        this.uid=uid;
        this.session = session;
        webSocketSet.put(uid,this);
        Integer userId=Integer.parseInt(uid);
        Integer newMsgtotal =userService.useMsgs(userId);

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("newMsgtotal",newMsgtotal);




        try {
            this.session.getBasicRemote().sendText(jsonObject.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
            }

    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(uid);
        log.error("连接断开，连接总数：{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
//        try {JSONObject messageObj=JSONObject.parseObject(message);
//        String msg=messageObj.getString("msg");
//        String fromUid=messageObj.getString("fromId");
//        String toUid=messageObj.getString("toId");
//        WebSocket webSocket=webSocketSet.get("toId");
//        userService.buildingBroker(1);
//        if(webSocket!=null)
//
//            this.session.getBasicRemote().sendText(message);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        log.error("收到客户端：{}，发来的消息：{}", namesMap.get(session.getId()), message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("websocket发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) {

        for (WebSocket webSocket : webSocketSet.values()) {
            log.error("广播消息，message={}", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void sendWholeAsyncMessage(String message,String toId) {

            try {
                WebSocket webSocket=webSocketSet.get(toId);
                if(webSocket!=null){
                    webSocket.session.getAsyncRemote().sendText(message);
                }


            } catch (Exception e) {
               e.printStackTrace();
            }

    }



}
