package com.github.wegoo.network.engine.processor;

import io.netty.buffer.ByteBuf;

/**
 * @author: zhangzhenwei
 * @description: BaseServerMessagePostProcessor
 *  消息基础处理类
 * @date: 2022/12/20  21:13
 * @since: 1.0.0
 */
public interface BaseMessagePostProcessor<T> {

  /**
   * @author zhangzhenwei
   * @description 读取buff中数据，并进行解析成指定对象
   * @date 2022/12/21  16:04
   * @since: 1.0.0
   */
  T postProcessReadByteBuf(ByteBuf obj);

  /**
   * @author zhangzhenwei
   * @description 核心业务处理 ，处理解析buf所得的对象进行进一步处理。
   * @date 2022/12/21  16:04
   * @return
   *  核心处理逻辑，若返回数据则表示有应答，返回null则表示不应答。
   * @since: 1.0.0
   */
  T postProcessMessage(T message);

  /**
   * @author zhangzhenwei
   * @description 发送消息前的处理逻辑，将消息转换为byte[]
   * @date 2022/12/21  16:47
   * @since: 1.0.0
   */
  byte[] postProcessBeforeSendMessage(T message);


}
