package com.zyl.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.zyl.pojo.RequestMessage;
import com.zyl.pojo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
public class BroadcastCtl {

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);
    
    /**
     * @MessageMapping 指定要接收消息的地址，类似@RequestMapping。除了注解到方法上，也可以注解到类上
     * @SendTo默认 消息将被发送到与传入消息相同的目的地
     * 消息的返回值是通过{@link org.springframework.messaging.converter.MessageConverter}进行转换
     * @param requestMessage
     * @return
     */
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public ResponseMessage broadcast(RequestMessage requestMessage){
        log.info("receive message = {}" , JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setContent("BroadcastCtl receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }

    @MessageMapping("/face")
    @SendTo("/topic/greetings")
    public ResponseMessage broadcastFace(RequestMessage requestMessage){
        log.info("receive message = {}" , JSONObject.toJSONString(requestMessage));
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setContent("BroadcastCtl receive [" + count.incrementAndGet() + "] records");
        return responseMessage;
    }

//    @RequestMapping(value="/broadcast/index")
//    public String broadcastIndex(HttpServletRequest req){
//        System.out.println(req.getRemoteHost());
//        return "websocket/simple/ws-broadcast";
//    }

}