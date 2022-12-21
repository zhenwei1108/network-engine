package com.github.wegoo.network.engine.client.future;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 * @author: zhangzhenwei
 * @description: TimeStampFuture
 *  带有时间戳的回调，时间戳用于清除过期消息
 * @date: 2022/12/21  22:24
 * @since: 1.0.0
 */
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
