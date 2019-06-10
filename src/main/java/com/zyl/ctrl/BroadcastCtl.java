package com.zyl.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.zyl.config.WebSocketServer;
import com.zyl.pojo.RequestMessage;
import com.zyl.pojo.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Controller
public class BroadcastCtl {

    // 收到消息记数
    private AtomicInteger count = new AtomicInteger(0);
//    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
    private ExecutorService executorService = Executors.newSingleThreadExecutor();




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


//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private WebSocketServer webSocketServer;

    @GetMapping(value="/pushimage")
    @ResponseBody
    public String broadcastIndex(HttpServletRequest req) throws IOException {
        List<byte[]> images = getImages();
        executorService.submit(new ImagePush(images,webSocketServer));
        return "success";
    }

    public List<byte[]> getImages() throws IOException {
        List<byte[]> images = new ArrayList<>();
        for (int i = 1; i <= 42; i++) {
            byte[] bytes = Files.readAllBytes(Paths.get(getImagePath(i)));
            images.add(bytes);
        }
        return images;
    }
    public String getImagePath(int i){
//        return String.format("C:\\Users\\Administrator\\Desktop\\bin\\image-%s.jpg",String.format("%03d",i));
        return String.format("C:\\Users\\Administrator\\Desktop\\bin\\test1\\IMG_20190606_144951_BURST%s.jpg",i);
    }

}