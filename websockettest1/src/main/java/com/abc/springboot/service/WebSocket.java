package com.abc.springboot.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("webSocket 有新的连接，总数：" + webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("webSocket 连接断开，总数：" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到客户端信息：" + message);
    }

    public void sendMessage(String message) {
        for (WebSocket socket : webSocketSet) {
            System.out.println("发送广播消息 ：" + message);
            try {
                socket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
