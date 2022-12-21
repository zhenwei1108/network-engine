package com.github.wegoo.network.engine.client.handler;

import com.github.wegoo.network.engine.BaseClientMessagePostProcessor;
import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.client.future.FutureHolder;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei
 * @description: NettyClientChannelHandler
 *  客户端消息核心处理者，通过 BaseMessagePostProcessor完成消息处理
 *  收到应答消息后，先经decoder处理后，再在这里进一步处理
 * @date: 2022/12/21  17:20
 * @since: 1.0.0
 */
@AllArgsConstructor
public class NettyClientChannelHandler extends SimpleChannelInboundHandler<BaseMessage> {

  private BaseClientMessagePostProcessor<BaseMessage> baseMessageBaseClientMessagePostProcessor;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
    FutureHolder.success(123123123, msg.getData());
    baseMessageBaseClientMessagePostProcessor.postProcessMessage(msg);
  }
}
