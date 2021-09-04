8.（必做）基于 Redis 封装分布式数据操作：

```
StockController内buy方法实现了分布式锁以及减库存操作
调用方式：http://localhost:8080/buy
```

9.（必做）基于 Redis 的 PubSub 实现订单异步处理
```aidl
PublishController实现了发送端
调用方式：http://localhost:8080/publish?message=11111
Receiver则是订阅端
```