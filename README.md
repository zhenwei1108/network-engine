# network-engine
# 互联网通信sdk，基于netty实现，支持自定义协议实现

## client
提供默认的 TimeStampFuture 实现。
在构造客户端时，需要使用BaseMessagePostProcessor完成业务处理


##Server
构造时使用BaseMessagePostProcessor完成业务处理。并建议使用独立线程启动服务端

## demo
详细示例见test
