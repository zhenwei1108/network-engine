package com.github.wegoo.network.engine.client.coder;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessagePostProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NettyClientEncoder extends MessageToByteEncoder<BaseMessage> {

  private BaseMessagePostProcessor<BaseMessage> baseMessageBaseMessagePostProcessor;

  @Override
  protected void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception {
    out.writeBytes(baseMessageBaseMessagePostProcessor.postProcessMessageToBytes(msg));
  }
}
