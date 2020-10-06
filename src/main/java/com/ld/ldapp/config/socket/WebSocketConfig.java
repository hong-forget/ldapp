package com.ld.ldapp.config.socket;

import com.ld.ldapp.service.UserService;
import com.ld.ldapp.util.JwtToken;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Slf4j
@Configuration
@EnableWebSocket
public class WebSocketConfig extends ServerEndpointConfig.Configurator{

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    @Autowired
    public void setUserService(UserService userService){
        WebSocket.userService=userService;
    }

    /**
     * token鉴权认证 临时写死123
     * @param originHeaderValue
     * @return
     */
    @Override
    public boolean checkOrigin(String originHeaderValue) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getParameter("token");
        log.info("token'{}'",token);
        Claims claims= JwtToken.getClaimByToken(token);
        log.info("claims'{}'",claims);
        if(claims!=null) {
            return super.checkOrigin(originHeaderValue);
        }else{
            return false;
        }
    }

    /**
     * Modify the WebSocket handshake response
     * 修改websocket 返回值
     * @param sec
     * @param request
     * @param response
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        super.modifyHandshake(sec, request, response);
    }


}
