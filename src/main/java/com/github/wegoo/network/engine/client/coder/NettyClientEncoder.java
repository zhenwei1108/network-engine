package com.github.wegoo.network.engine.client.coder;

import com.github.wegoo.network.engine.message.BaseMessage;
import com.github.wegoo.network.engine.processor.BaseMessagePostProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei 
 * @description: NettyClientEncoder
 *  客户端，发送消息时编码
 * @date: 2022/12/26  21:27
 * @since: 1.0.0 
 */
@AllArgsConstructor
public class NettyClientEncoder extends MessageToByteEncoder<BaseMessage> {

  private BaseMessagePostProcessor<BaseMessage> processor;

  @Override
  protected void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception {
    out.writeBytes(processor.postProcessBeforeSendMessage(msg));
  }
}
