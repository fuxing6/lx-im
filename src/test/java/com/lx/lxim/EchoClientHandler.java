package com.lx.lxim;

import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends CustomHeartbeatHandler {

  private EchoClient client;

  public EchoClientHandler(EchoClient client) {
    super("client");
    this.client = client;
  }


  @Override
  protected void handleData(ChannelHandlerContext channelHandlerContext, String msg) {
    System.out.println(name + " get content: " + msg);
  }

  @Override
  protected void handleAllIdle(ChannelHandlerContext ctx) {
    super.handleAllIdle(ctx);
    sendPingMsg(ctx);
  }


  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    client.doConnect();
  }
}
