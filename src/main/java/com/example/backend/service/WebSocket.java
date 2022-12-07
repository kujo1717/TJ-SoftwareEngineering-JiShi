package com.example.backend.service;

import com.example.backend.dto.FormatMessage;
import com.mysql.jdbc.log.Log;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author 2051196 刘一飞
 * @Date 2022/12/4
 * @JDKVersion 17.0.4
 */
@Component
@ServerEndpoint(value = "/webSocket", encoders = WebSocketCustomEncoding.class)
public class WebSocket {
    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("有新的连接，总数" + webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("连接断开，总数" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("收到客户端发来的消息");
    }

    public void sendMessage(FormatMessage formatMessage) {
        for (WebSocket webSocket : webSocketSet) {
            System.out.println("广播消息" + formatMessage);
            try {
                webSocket.session.getBasicRemote().sendObject(formatMessage);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (EncodeException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
