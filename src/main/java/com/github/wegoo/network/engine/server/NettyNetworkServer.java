package com.github.wegoo.network.engine.server;

import com.github.wegoo.network.engine.server.handler.DefaultMessageHandler;
import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessageHandler;
import com.github.wegoo.network.engine.server.coder.NettyServerDecoder;
import com.github.wegoo.network.engine.server.coder.NettyServerEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyNetworkServer implements INetworkServer {

  public void server(int port, BaseMessageHandler<BaseMessage> baseMessageHandler)
      throws InterruptedException {
    NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
    NioEventLoopGroup workGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap serverBootstrap = new ServerBootstrap();
      serverBootstrap
          .channel(NioServerSocketChannel.class)
          .group(bossGroup, workGroup)
          .handler(new LoggingHandler(LogLevel.INFO))
          .childHandler(new ChannelInitializer<NioSocketChannel>() {

            @Override
            protected void initChannel(NioSocketChannel channel) throws Exception {
              channel.pipeline()
                  .addLast(new NettyServerDecoder(baseMessageHandler))
                  .addLast(new NettyServerEncoder(baseMessageHandler))
                  .addLast(new DefaultMessageHandler(baseMessageHandler));
            }
          });
      ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
      channelFuture.channel().closeFuture().sync();
    } finally {
      bossGroup.shutdownGracefully();
      workGroup.shutdownGracefully();
    }
  }


}
