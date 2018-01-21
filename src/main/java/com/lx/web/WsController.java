package com.lx.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.lx.model.WiselyMessage;
import com.lx.model.WiselyResponse;

@Controller
public class WsController {
  // 当浏览器向服务器发送请求的时候通过@MessageMapping 映射/welcome 这个地址 类似 RequestMapping
  @MessageMapping("/welcome")
  // 当服务器有消息的时候，会对订阅了@SendTo中的路径浏览器发送消息
  @SendTo("/topic/getResponse")
  public WiselyResponse say(WiselyMessage wiselyMessage) throws InterruptedException {
    return new WiselyResponse("Welcome," + wiselyMessage.getName());
  }
}
