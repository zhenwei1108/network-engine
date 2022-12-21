package com.github.wegoo.network.engine.server.handler;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessagePostProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei 
 * @description: NettyServerChannelHandler
 *  处理读取到的消息，已经完成了沾包、拆包的处理。此处的消息为完整消息。
 * @date: 2022/12/20  21:54
 * @since: 1.0.0 
 */
@AllArgsConstructor
public class NettyServerChannelHandler extends SimpleChannelInboundHandler<BaseMessage> {

  private BaseMessagePostProcessor<BaseMessage> baseMessagePostProcessor;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
    ctx.writeAndFlush(baseMessagePostProcessor.postProcessMessage(msg));
  }
}
