package com.zxw.jwxt.service;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zxw
 * @date 2019/11/10 17:59
 */
@ServerEndpoint("/ws")
@Service
public class WebSocketServiceImpl {
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSocketSet = new CopyOnWriteArraySet<>();
    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
    }

    @OnClose
    public void onClose(Session session) {
        this.session = session;
        webSocketSet.remove(session);
    }

    @OnMessage
    public void onMessage(String msg, Session session) {

    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("[" + LocalDateTime.now() + "]:" + error.getMessage());
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }
}
