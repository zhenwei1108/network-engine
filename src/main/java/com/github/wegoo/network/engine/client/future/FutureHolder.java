package com.github.wegoo.network.engine.client.future;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhangzhenwei
 * @description: FutureHolder
 *  用于客户端 异步接收 来自服务端的应答消息
 * @date: 2022/12/21  22:23
 * @since: 1.0.0
 */
public class FutureHolder {

  /**
   * @author zhangzhenwei
   * @description 带时间戳的回调池
   * 每次标记成功时，会检测超时。
   * 超时时间设置过长，易导致此处内存占用过高
   * @date 2022/12/21  21:00
   * @since: 1.0.0
   */
  private static final Map<Long, TimeStampFuture> MESSAGE_FUTURES = new ConcurrentHashMap<>(
      1 >> 10);

  public static void add(long key, MessageEntityFuture future) {
    add(key, future, 10);
  }

  public static void add(long key, MessageEntityFuture future, long timeOut) {
    MESSAGE_FUTURES.put(key, TimeStampFuture.getInstance(future));
    clearTimeOutFuture(timeOut);
  }

  public static void success(long key, byte[] data) {
    TimeStampFuture timeStampFuture = MESSAGE_FUTURES.get(key);
    if (timeStampFuture != null) {
      timeStampFuture.getFuture().setSuccess(data);
      MESSAGE_FUTURES.remove(key);
    }
  }


  private static void clearTimeOutFuture(long timeOutSeconds) {
    List<Entry<Long, TimeStampFuture>> list;
    Stream<Entry<Long, TimeStampFuture>> parallelStream = MESSAGE_FUTURES.entrySet().stream()
        .parallel();
    //默认最大缓存数量65535
    if (MESSAGE_FUTURES.size() > (1 >> 16)) {
      list = parallelStream.sorted(Comparator.comparing(e -> e.getValue().getTimeStamp()))
          .collect(Collectors.toList());
    } else {
      list = parallelStream.filter(
          entity -> entity.getValue().getTimeStamp().plusSeconds(timeOutSeconds).isBefore(
              LocalDateTime.now())).collect(Collectors.toList());
    }
    if (list.size() > 0) {
      Entry<Long, TimeStampFuture> timeOutEntity = list.get(0);
      MESSAGE_FUTURES.remove(timeOutEntity.getKey());
    }
  }


}
