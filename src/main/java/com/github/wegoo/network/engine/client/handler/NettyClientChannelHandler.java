package com.github.wegoo.network.engine.client.handler;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessagePostProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei 
 * @description: NettyClientChannelHandler
 *  客户端消息核心处理者，通过 BaseMessagePostProcessor完成消息处理
 * @date: 2022/12/21  17:20
 * @since: 1.0.0 
 */
@AllArgsConstructor
public class NettyClientChannelHandler extends SimpleChannelInboundHandler<BaseMessage> {

  private BaseMessagePostProcessor<BaseMessage> baseMessageBaseMessagePostProcessor;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
    ctx.writeAndFlush(baseMessageBaseMessagePostProcessor.postProcessMessage(msg));
  }
}
