package com.github.wegoo.network.engine.message;

public class EchoMessage implements BaseMessage{

  private byte[] encode;

  @Override
  public byte[] getEncode() {
    return encode;
  }


  public void setEncode(byte[] message) {
    this.encode = message;
  }
}
