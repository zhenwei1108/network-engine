package com.github.wegoo.network.engine.processor;

import com.github.wegoo.network.engine.message.BaseMessage;
import com.github.wegoo.network.engine.message.EchoMessage;
import io.netty.buffer.ByteBuf;

/**
 * @author: zhangzhenwei 
 * @description: EchoServerMessagePostProcessor
 *  服务端 回音壁 实现
 * @date: 2022/12/23  10:53
 * @since: 1.0.0 
 */
public class EchoServerMessagePostProcessor implements BaseMessagePostProcessor<BaseMessage>{

  @Override
  public BaseMessage postProcessReadByteBuf(ByteBuf buf) {
    byte[] data = new byte[buf.writerIndex()];
    EchoMessage message = new EchoMessage();
    buf.readBytes(data);
    message.setEncode(data);
    return message;
  }

  @Override
  public BaseMessage postProcessMessage(BaseMessage message) {
    return message;
  }

  @Override
  public byte[] postProcessBeforeSendMessage(BaseMessage message) {
    return message.getEncode();
  }
}
