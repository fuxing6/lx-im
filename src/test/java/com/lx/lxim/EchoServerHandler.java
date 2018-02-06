package com.lx.lxim;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class EchoServerHandler extends CustomHeartbeatHandler {
  public EchoServerHandler() {
    super("server");
  }

  @Override
  protected void handleData(ChannelHandlerContext channelHandlerContext, String msg) {
    System.out.println(name + " get content: " + msg);
    ByteBuf wbuf = Unpooled.buffer(5 + msg.getBytes().length);
    wbuf.writeInt(msg.getBytes().length);
    wbuf.writeByte(CustomHeartbeatHandler.CUSTOM_MSG);
    wbuf.writeBytes(msg.getBytes());
    channelHandlerContext.write(wbuf);
  }

  @Override
  protected void handleReaderIdle(ChannelHandlerContext ctx) {
    super.handleReaderIdle(ctx);
    System.err.println("---client " + ctx.channel().remoteAddress().toString()
        + " reader timeout, close it---");
    ctx.close();
  }
}
