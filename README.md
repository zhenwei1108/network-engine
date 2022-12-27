# network-engine
# 互联网通信sdk，基于netty实现，支持自定义协议实现

## client
提供默认的 TimeStampFuture 实现。
在构造客户端时，需要使用BaseMessagePostProcessor完成业务处理


##Server
构造时使用BaseMessagePostProcessor完成业务处理。并建议使用独立线程启动服务端

## demo
详细示例见test


## 系统参数配置
1. SO_SNDBUF = 4k : TCP发送缓冲区大小
2. SO_RCVBUF = 4k ：TCP接受缓冲区大小
3. SO_KEEPALIVE = false : TCP层keepalive
4. SO_REUSEADDR = false : 地址重用，多网卡绑定相同端口
5. SO_LINGER = false : 关闭socket延迟时间，socket.close()立即返回
6. TCP_NODELAY = false : 是否启用Nagle算法，将多个小包合并成一个大包，提高发送效率。