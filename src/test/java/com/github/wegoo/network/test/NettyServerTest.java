package com.github.wegoo.network.test;

import com.github.wegoo.network.engine.BaseMessage;
import com.github.wegoo.network.engine.BaseMessagePostProcessor;
import com.github.wegoo.network.engine.server.NettyNetworkServer;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

public class NettyServerTest {

  @Test
  public void testServer() throws InterruptedException {

    NettyNetworkServer nettyNetworkServer = new NettyNetworkServer();
    TestBaseMessageHandler handler = new TestBaseMessageHandler();
    nettyNetworkServer.server(9999, handler);

  }


  class TestBaseMessageHandler implements BaseMessagePostProcessor<BaseMessage> {

    @Override
    public BaseMessage postProcessReadByteBuf(ByteBuf buf) {
      byte[] array = new byte[6];
      buf.readBytes(array);
      System.out.println("pre handler :" + Hex.toHexString(array));
      ProtocolChannel protocolChannel = new ProtocolChannel();
      protocolChannel.setData(array);
      protocolChannel.setMessageId(22222222);
      protocolChannel.setLen(array.length);
      protocolChannel.setType("安全通道");
      return protocolChannel;
    }

    @Override
    public BaseMessage postProcessMessage(BaseMessage message) {
      if (message instanceof ProtocolChannel) {
        ProtocolChannel channel = (ProtocolChannel) message;
        System.out.println("doHandler:" + channel.getType());
        System.out.println("doHandler:" + Hex.toHexString(channel.getData()));
        channel.setData(new byte[]{1, 2, 3, 4, 5, 6});
      }
      return message;
    }

    @Override
    public byte[] postProcessBeforeSendMessage(BaseMessage message) {
      if (message instanceof ProtocolChannel) {
        ProtocolChannel channel = (ProtocolChannel) message;
        System.out.println("afterHandler:" + Hex.toHexString(channel.getData()));
        return channel.getData();
      }
      return null;
    }
  }


  @Data
  class ProtocolChannel implements BaseMessage {

    private long messageId;
    private String type;
    private int len;
    private byte[] data;
  }

}
