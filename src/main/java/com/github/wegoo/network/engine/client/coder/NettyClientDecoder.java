package com.github.wegoo.network.engine.client.coder;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessagePostProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei
 * @description: NettyClientDecoder
 *  客户端接收应答消息时，首先在此处理
 * @date: 2022/12/21  16:01
 * @since: 1.0.0
 */
@AllArgsConstructor
public class NettyClientDecoder extends ReplayingDecoder<Void> {

  private BaseMessagePostProcessor<BaseMessage> processor;

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    out.add(processor.postProcessReadByteBuf(in));
  }
}
