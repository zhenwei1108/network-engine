package com.github.wegoo.network.engine.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyNetworkClient implements INetworkClient{

  public void client(String host, int port) throws InterruptedException {
    NioEventLoopGroup work = new NioEventLoopGroup(1);
    Bootstrap bootstrap = new Bootstrap();
    bootstrap.channel(NioSocketChannel.class)
        .group(work)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast()
                ;

          }
        }).connect(host,port).sync();
  }


}
