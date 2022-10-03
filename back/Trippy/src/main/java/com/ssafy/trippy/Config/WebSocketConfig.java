package com.ssafy.trippy.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
// 웹소켓 활성화
// websocket위에 stomp 프로토콜이 있다.
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /*어플리케이션 내부에서 사용할 path를 지정할 수 있음*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/sub");
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint란 api가 서버에서 resource에 접근할 수 있도록 하는 url
        //endpoint를 /stomp로 하고, allowedOrigins를 "*"로 하면 페이지에서
        //Get /info 404 Error가 발생한다. 그래서 아래와 같이 2개의 계층으로 분리하고
        //origins를 개발 도메인으로 변경하니 잘 동작하였다.
        //이유는 왜 그런지 아직 찾지 못함
        registry.addEndpoint("/chatting")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }
}
