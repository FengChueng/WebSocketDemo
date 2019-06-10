package com.zyl.ctrl;


/**
 * Created by Administrator on 2019/5/3.
 */
//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Autowired
//    private MyHandShakeInterceptor myHandShakeInterceptor;
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/websocket-face")
//                .addInterceptors(myHandShakeInterceptor)
//                .setAllowedOrigins("*")
//                .withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/topic");
//        registry.setApplicationDestinationPrefixes("/app");
//    }
//}
