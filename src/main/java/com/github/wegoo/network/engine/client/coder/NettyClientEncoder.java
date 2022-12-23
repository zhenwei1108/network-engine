package com.github.wegoo.network.engine.client.coder;

import com.github.wegoo.network.engine.message.BaseMessage;
import com.github.wegoo.network.engine.processor.BaseMessagePostProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NettyClientEncoder extends MessageToByteEncoder<BaseMessage> {

  private BaseMessagePostProcessor<BaseMessage> processor;

  @Override
  protected void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception {
    out.writeBytes(processor.postProcessBeforeSendMessage(msg));
  }
}
