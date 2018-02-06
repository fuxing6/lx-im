package com.lx.lxim.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeCLientHandler extends ChannelInboundHandlerAdapter {
  private byte[] req;

  private int counter;

  public TimeCLientHandler() {
    this.req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
  }


  /**
   * 链路建立成功时发送100条消息到服务端, 每发送一条就刷新一次数据到SocketChannel
   * 
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ByteBuf message = null;
    String content =
        "{\"businessEntryTime\":1549017111000,\"businessFinishTime\":1549017111000,\"entryTime\":1549017111000,\"finishTime\":1549017111000,\"fwPin\":\"cc115618b1194c90aa59beb81137ff65\",\"ip\":\"192.168.0.100\",\"responseBody\":\"\",\"responseCode\":\"0\",\"sid\":\"59c4f3e7-c79c-4d27-ae77-e7bafd95442c\",\"url\":\"/taskcenter/task/list\"}";
    content = content + System.getProperty("line.separator");
    for (int i = 0; i < 100; i++) {
      message = Unpooled.buffer(content.getBytes().length);
      message.writeBytes(content.getBytes());
      ctx.writeAndFlush(message);
      // TimeUnit.MICROSECONDS.sleep(1);
    }

  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    String body = (String) msg;
    System.out.println("client receive:" + body);
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    ctx.close();
  }
}
