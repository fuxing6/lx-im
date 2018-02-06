package com.lx.lxim;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public abstract class CustomHeartbeatHandler extends SimpleChannelInboundHandler<ByteBuf> {
  public static final byte PING_MSG = 1;
  public static final byte PONG_MSG = 2;
  public static final byte CUSTOM_MSG = 3;
  protected String name;

  public CustomHeartbeatHandler(String name) {
    this.name = name;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext context, ByteBuf byteBuf) throws Exception {
    if (byteBuf.getByte(4) == PING_MSG) {
      sendPongMsg(context);
    } else if (byteBuf.getByte(4) == PONG_MSG) {
      System.out.println(name + " get pong msg from " + context.channel().remoteAddress());
    } else {
      int readInt = byteBuf.readInt();
      System.err.println("readint = " + readInt);
      // byte[] data = new byte[byteBuf.readableBytes() - 5];
      // byteBuf.skipBytes(5);
      // byteBuf.readBytes(data);
      // String content = new String(data);
      // System.out.println(name + " get content: " + content);
      // handleData(context, content);
    }
  }

  protected void sendPingMsg(ChannelHandlerContext context) {
    ByteBuf buf = context.alloc().buffer(5);
    buf.writeInt(5);
    buf.writeByte(PING_MSG);
    buf.retain();
    context.writeAndFlush(buf);
  }

  private void sendPongMsg(ChannelHandlerContext context) {
    ByteBuf buf = context.alloc().buffer(5);
    buf.writeInt(5);
    buf.writeByte(PONG_MSG);
    context.channel().writeAndFlush(buf);
  }

  protected abstract void handleData(ChannelHandlerContext channelHandlerContext, String msg);

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    // IdleStateHandler 所产生的 IdleStateEvent 的处理逻辑.
    if (evt instanceof IdleStateEvent) {
      IdleStateEvent e = (IdleStateEvent) evt;
      switch (e.state()) {
        case READER_IDLE:
          handleReaderIdle(ctx);
          break;
        case WRITER_IDLE:
          handleWriterIdle(ctx);
          break;
        case ALL_IDLE:
          handleAllIdle(ctx);
          break;
        default:
          break;
      }
    }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.err.println("---" + ctx.channel().remoteAddress() + " is active---");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.err.println("---" + ctx.channel().remoteAddress() + " is inactive---");
  }

  protected void handleReaderIdle(ChannelHandlerContext ctx) {
    System.err.println("---READER_IDLE---");
  }

  protected void handleWriterIdle(ChannelHandlerContext ctx) {
    System.err.println("---WRITER_IDLE---");
  }

  protected void handleAllIdle(ChannelHandlerContext ctx) {
    System.err.println("---ALL_IDLE---");
  }
}
