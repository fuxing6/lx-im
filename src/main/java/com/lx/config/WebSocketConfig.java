package com.lx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * 
 * @author wufuxing
 *
 * @time 2018年1月21日上午1:04:50
 */

@Configuration
// 开启stomp协议来传输基于代理message broker的消息
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

  @Override
  // 注册stomp协议节点 endpoint
  public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
    // 注册一个endpoint 并指定是哟个socketjs
    stompEndpointRegistry.addEndpoint("/endpointWisely").withSockJS();
  }

  @Override
  // 配置消息代理
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 广播式配置一个topic的消息代理
    registry.enableSimpleBroker("/topic");
  }
}
