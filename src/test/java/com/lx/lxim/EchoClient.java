package com.lx.lxim;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

public class EchoClient {

  private NioEventLoopGroup workGroup = new NioEventLoopGroup(4);
  private Channel channel;
  private Bootstrap bootstrap;
  private final String host;
  private final int port;

  public EchoClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  public void start() throws Exception {
    bootstrap = new Bootstrap(); // 1
    bootstrap.group(workGroup) // 2
        .channel(NioSocketChannel.class) // 3
        .handler(new ChannelInitializer<SocketChannel>() { // 5
              @Override
              public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline p = ch.pipeline();
                // p.addLast(new IdleStateHandler(0, 0, 5));
                // p.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4, 0));
                p.addLast(new EchoClientHandler(EchoClient.this));
              }
            });
    doConnect();

  }

  /**
   * 重连机制,每隔2s重新连接一次服务器
   */
  protected void doConnect() {
    if (channel != null && channel.isActive()) {
      return;
    }

    ChannelFuture future = bootstrap.connect("127.0.0.1", 8888);

    future.addListener(new ChannelFutureListener() {
      public void operationComplete(ChannelFuture futureListener) throws Exception {
        if (futureListener.isSuccess()) {
          channel = futureListener.channel();
          System.out.println("Connect to server successfully!");
        } else {
          System.out.println("Failed to connect to server, try connect after 2s");

          futureListener.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
              doConnect();
            }
          }, 2, TimeUnit.SECONDS);
        }
      }
    });
  }

  public void sendData() throws Exception {
    // for (int i = 0; i < 100; i++) {
    if (channel != null && channel.isActive()) {
      String content =
          "{\"businessEntryTime\":1549017111000,\"businessFinishTime\":1549017111000,\"entryTime\":1549017111000,\"finishTime\":1549017111000,\"fwPin\":\"cc115618b1194c90aa59beb81137ff65\",\"ip\":\"192.168.0.100\",\"responseBody\":\"\",\"responseCode\":\"0\",\"sid\":\"59c4f3e7-c79c-4d27-ae77-e7bafd95442c\",\"url\":\"/taskcenter/task/list\"}";
      content = content + System.getProperty("line.separator");
      ByteBuf buf = channel.alloc().buffer(content.getBytes().length);
      System.out.println(content.getBytes().length);
      buf.writeBytes(content.getBytes());
      channel.writeAndFlush(buf);
      // }
      // TimeUnit.SECONDS.sleep(1);
    }
  }

  public static void main(String[] args) throws Exception {

    final String host = "192.168.0.101";
    final int port = 6460;
    EchoClient client = new EchoClient(host, port);
    client.start();
    client.sendData();
  }
}
