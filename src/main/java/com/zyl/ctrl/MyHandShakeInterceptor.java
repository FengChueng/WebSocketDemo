package com.zyl.ctrl;

//@Component
//public class MyHandShakeInterceptor implements HandshakeInterceptor {
//    @Override
//    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
//        System.out.println(this.getClass().getCanonicalName() + "http协议转换websoket协议进行前, 握手前"+request.getURI());
//        // http协议转换websoket协议进行前，可以在这里通过session信息判断用户登录是否合法
//        return true;
//    }
//
//    @Override
//    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
//        //握手成功后,
//        System.out.println(this.getClass().getCanonicalName() + "握手成功后...");
//    }
//}