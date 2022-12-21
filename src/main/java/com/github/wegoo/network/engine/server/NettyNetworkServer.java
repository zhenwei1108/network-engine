package com.github.wegoo.network.engine.server;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessageHandler;
import com.github.wegoo.network.engine.server.coder.NettyServerDecoder;
import com.github.wegoo.network.engine.server.coder.NettyServerEncoder;
import com.github.wegoo.network.engine.server.handler.NettyServerChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: zhangzhenwei 
 * @description: NettyNetworkServer
 *  服务端实现， 服务端要持久运行，不支持关闭
 * @date: 2022/12/21  15:56
 * @since: 1.0.0 
 */
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
          //todo 后续补充完善
          .option(ChannelOption.AUTO_CLOSE, true)
          .childHandler(new ChannelInitializer<NioSocketChannel>() {

            @Override
            protected void initChannel(NioSocketChannel channel) throws Exception {
              channel.pipeline()
                  .addLast(new NettyServerDecoder(baseMessageHandler))
                  .addLast(new NettyServerEncoder(baseMessageHandler))
                  .addLast(new NettyServerChannelHandler(baseMessageHandler));
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
