package com.github.wegoo.network.engine.client.future;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FutureHolder {

  /**
   * @author zhangzhenwei
   * @description 带时间戳的回调
   * @date 2022/12/21  21:00
   * @since: 1.0.0
   */
  private static final Map<Long, TimeStampFuture> MESSAGE_FUTURES = new ConcurrentHashMap<>(16);

  public static void add(long key, MessageEntityFuture future) {
    MESSAGE_FUTURES.put(key, TimeStampFuture.getInstance(future));
  }

  public static void success(long key, byte[] data) {
    TimeStampFuture timeStampFuture = MESSAGE_FUTURES.get(key);
    if (timeStampFuture != null) {
      timeStampFuture.getFuture().setSuccess(data);
      MESSAGE_FUTURES.remove(key);
    }
  }


}
