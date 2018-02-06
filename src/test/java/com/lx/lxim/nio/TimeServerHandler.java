package com.lx.lxim.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {

  private int counter;

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    String body = (String) msg;
    // String currentTime =
    // "QUERY TIME ORDER".equalsIgnoreCase(body) ? UUID.randomUUID().toString() : "BAD ORDER";
    body = body + System.getProperty("line.separator");
    System.out.println("server :" + body);
    // response
    ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
    // 异步发送应答消息给客户端: 这里并没有把消息直接写入SocketChannel,而是放入发送缓冲数组中
    ctx.writeAndFlush(resp);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    // 将发送缓冲区中数据全部写入SocketChannel
    // ctx.flush();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    // 释放资源
    ctx.close();
  }
}
