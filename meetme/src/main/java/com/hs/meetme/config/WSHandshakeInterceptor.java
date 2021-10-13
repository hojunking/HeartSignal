package com.hs.meetme.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.hs.meetme.useraccess.domain.AccountVO;

public class WSHandshakeInterceptor implements HandshakeInterceptor {
    
	public static Map<String, String> map = new HashMap<String, String>();
	
	@Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        return true;
    }


    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        HttpHeaders header = serverHttpRequest.getHeaders();
//        String client = header.get("user-name").get(0);
        String email = null;
        String sessionId = null;
//        if (serverHttpRequest instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;
            HttpSession session = servletRequest.getServletRequest().getSession();
            email = ((AccountVO)session.getAttribute("userSession")).getEmail();
            sessionId = ((AccountVO)session.getAttribute("userSession")).getName();
            System.out.println("Session ID + "+sessionId);
            map.put(email, sessionId);
            //System.out.println("CLIENT ID "+client);
//        }
    }
}
