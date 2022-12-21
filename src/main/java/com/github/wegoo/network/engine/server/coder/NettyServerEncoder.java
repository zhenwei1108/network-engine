package com.github.wegoo.network.engine.server.coder;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseServerMessagePostProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei 
 * @description: NettyServerEncoder
 *  发消息时处理机制
 * @date: 2022/12/21  17:17
 * @since: 1.0.0 
 */
@AllArgsConstructor
public class NettyServerEncoder extends MessageToByteEncoder<BaseMessage> {

  private BaseServerMessagePostProcessor<BaseMessage> baseServerMessagePostProcessor;

  @Override
  protected void encode(ChannelHandlerContext ctx, BaseMessage msg, ByteBuf out) throws Exception {
    out.writeBytes(baseServerMessagePostProcessor.postProcessBeforeSendMessage(msg));
  }
}
