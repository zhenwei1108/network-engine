package com.github.wegoo.network.engine.server.coder;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessageHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NettyServerEncoder extends MessageToByteEncoder<BaseMessage> {

  private BaseMessageHandler<BaseMessage> baseMessageHandler;

  @Override
  protected void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception {
    out.writeBytes(baseMessageHandler.afterHandler(msg));
  }
}
