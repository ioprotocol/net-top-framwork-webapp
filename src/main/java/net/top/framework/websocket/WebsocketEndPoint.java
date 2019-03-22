package net.top.framework.websocket;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @Project:net-top-framwork
 * @Package net.top.framework.controller
 * @Description:
 * @author: xsy
 * @date： 2016/6/3
 * @version： V1.0
 */
public class WebsocketEndPoint extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        super.handleBinaryMessage(session, message);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        session.sendMessage(returnMessage);
    }
}
