package com.github.wegoo.network.engine;

import io.netty.buffer.ByteBuf;

/**
 * @author: zhangzhenwei
 * @description: BaseMessagePostProcessor
 *  消息基础处理类
 * @date: 2022/12/20  21:13
 * @since: 1.0.0
 */
public interface BaseMessagePostProcessor<T> {

  /**
   * @author zhangzhenwei
   * @description 读取buff中数据，并进行解析
   * @date 2022/12/21  16:04
   * @since: 1.0.0
   */
  T postProcessByteBufToMessage(ByteBuf obj);

  /**
   * @author zhangzhenwei
   * @description 核心业务处理 ，处理解析buff后的数据
   * @date 2022/12/21  16:04
   * @return 接收一个消息（接口基类）request，返回另外一个基类response。
   *  可以使用同样的顶级父类
   * @since: 1.0.0
   */
  T postProcessMessage(T message);

  /**
   * @author zhangzhenwei
   * @description 将消息转换为byte[]
   * @date 2022/12/21  16:47
   * @since: 1.0.0
   */
  byte[] postProcessMessageToBytes(T message);


}
