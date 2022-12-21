package com.github.wegoo.network.engine;

import io.netty.buffer.ByteBuf;

/**
 * @author: zhangzhenwei
 * @description: BaseMessageHandler
 *  消息基础处理类
 * @date: 2022/12/20  21:13
 * @since: 1.0.0
 */
public interface BaseMessageHandler<T> {

  T preHandler(ByteBuf obj);

  void doHandler(T message);

  byte[] afterHandler(T message);


}
