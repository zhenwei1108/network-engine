package com.github.wegoo.network.engine.processor;

import com.github.wegoo.network.engine.message.BaseMessage;
import com.github.wegoo.network.engine.message.EchoMessage;
import io.netty.buffer.ByteBuf;

/**
 * @author: zhangzhenwei
 * @description: EchoClientMessagePostProcessor
 *  客户端 回音壁 实现
 * @date: 2022/12/23  10:47
 * @since: 1.0.0
 */
public class EchoClientMessagePostProcessor implements BaseMessagePostProcessor<BaseMessage> {

  /**
   * @author zhangzhenwei
   * @description 读取服务端应答消息
   * 将消息由 {@link ByteBuf} 解析成 {@link BaseMessage}
   * @param buf : netty定义的buf
   * @return 返回 BaseMessage ，基础消息，由具体实现完成处理
   * @date 2022/12/23  10:47
   * @since: 1.0.0
   */
  @Override
  public BaseMessage postProcessReadByteBuf(ByteBuf buf) {
    byte[] data = new byte[buf.writerIndex()];
    EchoMessage message = new EchoMessage();
    buf.readBytes(data);
    message.setEncode(data);
    return message;
  }

  /**
   * @author zhangzhenwei
   * @description 核心业务处理。将读取到的消息进一步处理。可以做出应答
   * @param message ： 接受服务端消息的处理结果
   * @return BaseMessage，为客户端处理结果的应答。可以不返回数据
   * 返回null，表示客户端收到消息后不再应答
   * @date 2022/12/23  10:49
   * @since: 1.0.0
   */
  @Override
  public BaseMessage postProcessMessage(BaseMessage message) {
    if (message instanceof EchoMessage) {
      byte[] encode = message.getEncode();
      ((EchoMessage) message).setEncode(encode);
    }
    return null;
  }

  /**
   * @author zhangzhenwei
   * @description 发送消息前的处理逻辑
   * @param message 发送的消息
   * @return 将消息转换为byte[]
   * @date 2022/12/23  10:52
   * @since: 1.0.0
   */
  @Override
  public byte[] postProcessBeforeSendMessage(BaseMessage message) {
    return message.getEncode();
  }
}
