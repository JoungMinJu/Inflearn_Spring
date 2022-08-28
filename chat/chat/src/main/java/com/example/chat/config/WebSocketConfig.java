package com.example.chat.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// WebSocket end-point 및 message broker 구성하기
@Configuration
@EnableWebSocketMessageBroker // webSocket의 서버를 활성화하는데 사용
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    // 클라이언트가 웹 소켓 서버에 연결하는데 사용할 웹 소켄 엔드 포인트를 등록한다.
    // SockJS는 웹소켓을 지원하지 않는 브라우저에 fallback 옵션을 활성화하는데 사용한다.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    // 한 클라이언트에서 다른 클라이언트로 메세지를 라우티아는데 사용될 메세지 브로커를 구성하고 있음
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // /app으로 시작되는 메시지가 message-handling methods로 라우팅되어야한다는 것을 의미
        registry.setApplicationDestinationPrefixes("/app");
        // /topic으로 시작되는메세지가 메세지 브로커로 라우팅 되도록 정의
        registry.enableSimpleBroker("/topic");
    }
}
