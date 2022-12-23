package com.github.wegoo.network.engine.server.handler;

import com.github.wegoo.network.engine.message.BaseMessage;
import com.github.wegoo.network.engine.processor.BaseMessagePostProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei
 * @description: NettyServerChannelHandler
 *  接收到消息后，第二（核心）处理人
 *  处理读取到的消息，已经完成了沾包、拆包的处理。此处的消息为完整消息。
 * @date: 2022/12/20  21:54
 * @since: 1.0.0
 */
@AllArgsConstructor
public class NettyServerChannelHandler extends SimpleChannelInboundHandler<BaseMessage> {

  private BaseMessagePostProcessor<BaseMessage> baseMessagePostProcessor;

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, BaseMessage msg) throws Exception {
    BaseMessage responseMessage = baseMessagePostProcessor.postProcessMessage(msg);
    //判断服务端是否响应消息
    if (responseMessage != null) {
      ctx.writeAndFlush(responseMessage);
    }
  }
}
