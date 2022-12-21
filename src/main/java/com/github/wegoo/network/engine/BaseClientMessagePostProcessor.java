package com.github.wegoo.network.engine;

import io.netty.buffer.ByteBuf;
/**
 * @author: zhangzhenwei 
 * @description: BaseClientMessagePostProcessor
 *  客户端消息处理基类
 * @date: 2022/12/21  20:17
 * @since: 1.0.0 
 */
public interface BaseClientMessagePostProcessor<T> {

  byte[] postProcessBeforeSendMessage(T message);

  T postProcessMessage(BaseMessage message);

  T postProcessReadByteBuf(ByteBuf buf);


}
