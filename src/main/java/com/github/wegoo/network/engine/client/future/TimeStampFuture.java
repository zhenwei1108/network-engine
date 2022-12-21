package com.github.wegoo.network.engine.client.future;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class TimeStampFuture {

  private LocalDateTime timeStamp = LocalDateTime.now();
  private MessageEntityFuture future;

  private TimeStampFuture(MessageEntityFuture future) {
    this.future = future;
  }

  public static TimeStampFuture getInstance(Object obj) {
    if (obj instanceof MessageEntityFuture) {
      MessageEntityFuture future = (MessageEntityFuture) obj;
      return new TimeStampFuture(future);
    } else if (obj instanceof TimeStampFuture) {
      return (TimeStampFuture) obj;
    }
    throw new RuntimeException("not support this type of:" + obj.getClass());
  }

}
