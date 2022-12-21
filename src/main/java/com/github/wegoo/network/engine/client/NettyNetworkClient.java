package com.github.wegoo.network.engine.client;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessagePostProcessor;
import com.github.wegoo.network.engine.client.coder.NettyClientDecoder;
import com.github.wegoo.network.engine.client.coder.NettyClientEncoder;
import com.github.wegoo.network.engine.client.handler.NettyClientChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: zhangzhenwei
 * @description: NettyNetworkClient
 *  客户端实现，可以创建多客户端发起请求
 * @date: 2022/12/21  15:56
 * @since: 1.0.0
 */
public class NettyNetworkClient implements INetworkClient {

  Channel channel;

  public void client(String host, int port, BaseMessagePostProcessor<BaseMessage> processor)
      throws InterruptedException {
    NioEventLoopGroup work = new NioEventLoopGroup(1);
    ChannelFuture future = new Bootstrap().channel(NioSocketChannel.class)
        .group(work)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline()
                .addLast(new NettyClientEncoder(processor))
                .addLast(new NettyClientDecoder(processor))
                .addLast(new NettyClientChannelHandler(processor))
            ;
          }
        }).connect(host, port).sync();
    channel = future.channel();
  }


  public void sendMessage(Object data) {
    this.channel.writeAndFlush(data);
  }

  public void close(){
    if (this.channel!=null){
      this.channel.close();
    }
  }




}
