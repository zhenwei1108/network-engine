package com.github.wegoo.network.engine.server.coder;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseServerMessagePostProcessor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * @author: zhangzhenwei 
 * @description: NettyServerDecoder
 *  服务端，接收到消息后，第一处理人
 *  负责处理沾包、拆包问题，根据自定义规则读取ByteBuf中的数据后，向下传递
 * @date: 2022/12/20  21:54
 * @since: 1.0.0 
 */
@AllArgsConstructor
public class NettyServerDecoder extends ReplayingDecoder<Void> {

  private BaseServerMessagePostProcessor<BaseMessage> baseServerMessagePostProcessor;

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    out.add(baseServerMessagePostProcessor.postProcessReadByteBuf(in));
  }
}
