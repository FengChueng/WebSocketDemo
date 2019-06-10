package com.zyl.ctrl;

import com.zyl.config.WebSocketServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

/**
 * @class_name:
 * @package:
 * @describe: TODO
 * @author: zhangfeng
 * @creat_date: 2019/6/5
 * @creat_time: 14:42
 **/
@Slf4j
public class ImagePush implements Runnable{

    private final List<byte[]> images;
    private final WebSocketServer messagingTemplate;

    public ImagePush(List<byte[]> images,WebSocketServer messagingTemplate){
        this.images = images;
        this.messagingTemplate = messagingTemplate;
    }


    @Override
    public void run() {

        while (true){
            log.info("发送图片。。。。" + images.size());
            images.forEach((action)->{
                try {
                    WebSocketServer.sendInfo(action);
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            log.info("发送图片结束。。。。");
        }
//        WebSocketServer.sendInfo(images);
        //读取16张图片
//        images.forEach((action)->{
////            Map<String, Object> header = new HashMap<>();
////            header.put("type","image");
////            messagingTemplate.send("/topic/event",message);
////            messagingTemplate.convertAndSend("/topic/event",action);
//            try {
//                messagingTemplate.sendMessage(action);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }
}
