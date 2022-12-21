package com.github.wegoo.network.test;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessagePostProcessor;
import com.github.wegoo.network.engine.client.NettyNetworkClient;
import com.github.wegoo.network.engine.client.future.FutureHolder;
import com.github.wegoo.network.engine.client.future.MessageEntityFuture;
import io.netty.buffer.ByteBuf;
import java.util.concurrent.ExecutionException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class NettyClientTest {

  @Test
  public void testClient() throws InterruptedException, ExecutionException {
    NettyNetworkClient client = new NettyNetworkClient();
    ClientMessagePostProcessor clientMessagePostProcessor = new ClientMessagePostProcessor();
    client.client("127.0.0.1", 9999, clientMessagePostProcessor);
    ClientMessage clientMessage = new ClientMessage();
    clientMessage.setData(new byte[]{1, 2, 3, 4, 5, 67, 8, 9});
    clientMessage.setMsg("this is message");
    clientMessage.setMessageId(123123123);
    MessageEntityFuture future = new MessageEntityFuture();
    FutureHolder.add(123123123, future, 10);
    client.sendMessage(clientMessage);
    //需要在接收到服务端的应答后，标记success，此处才可以get到
    byte[] bytes = future.get();
    System.out.println(Hex.toHexString(bytes));


  }


  class ClientMessagePostProcessor implements BaseMessagePostProcessor<BaseMessage> {

    @Override
    public BaseMessage postProcessReadByteBuf(ByteBuf buf) {
      byte[] data = new byte[6];
      buf.readBytes(data);
      ClientMessage clientMessage = new ClientMessage(Hex.toHexString(data), data, 123123123);
      System.out.println("客户端读取到消息:" + clientMessage);
      return clientMessage;
    }

    @Override
    public BaseMessage postProcessMessage(BaseMessage message) {
      System.out.println("客户端处理消息" + Hex.toHexString(message.getData()));
      return message;
    }

    @Override
    public byte[] postProcessBeforeSendMessage(BaseMessage message) {
      return message.getData();
    }
  }


  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  class ClientMessage implements BaseMessage {

    String msg;
    byte[] data;
    long messageId;
  }


}
