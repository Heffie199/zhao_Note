2. 观看架构师视屏之 多线程。 并记笔记

3. 观看黑马的设计模式视屏

4。 了解eclipse上的proxy-dowm项目

5。 总结 遇到的坑 left join group  sum

偏向锁、轻量级锁、重量级锁等。

JVM 的“假唤醒”

springBoot

springCloud

Java并发编程

Java内存模型的理解

lombok

https://mp.weixin.qq.com/s/b8ZCXHk8z4_RskoD0HxMMA

jdk 也提供了相似的支持，可以参考 java.util.Observable 和 java.util.Observer 这两个类。

实际生产过程中，观察者模式往往用消息中间件来实现，如果要实现单机观察者模式，建议使用 Guava 中的 EventBus，它有同步实现也有异步实现。