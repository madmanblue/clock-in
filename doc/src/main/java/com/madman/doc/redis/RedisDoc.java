package com.madman.doc.redis;

/**
 * redis简介
 *  redis是一个key-val的非关系型数据库，速度非常快
 *
 * 为什么快？
 *  纯内存操作
 *  单线程操作，避免了频繁的上下文切换
 *  采用了非阻塞I/O多路复用机制（多路复用需要详细看）
 *
 * redis数据类型以及对应的使用场景
 *  1 String
 *    做些复杂的计数缓存
 *  2 hash
 *    存的结构是map，可以用在单点登陆的用户信息存储，模拟session的效果
 *  3 list
 *    简单的消息队列功能，可以用lrange命令，做基于redis的分页功能
 *  4 set
 *    全局去重，可以在集群中使用，而不用JVM的set
 *  5 sorted set
 *    多了一个权重score，集合中的元素可以排序，可以做排行榜，topn操作。
 *    可以做延时任务。最后一个应用可以做范围查找？
 *
 * redis的过期策略
 *  采用定期删除+惰性删除
 *  默认100ms随机抽取检查，如果有过期的则删除。在获取key的时候，如果key设置了过期时间，会先判断是否过期，如果
 *  过期也会被删除。如果定期删除和惰性删除都没有将过期key删除，内存越来越大，则需要内存淘汰机制来进行处理
 * redis的内存淘汰机制
 *  maxmemory 100m 最大内存
 *  maxmemory-policy volatile-lru
 *  默认noevivation 内存不足时，新写入数据会报错
 *  allkeys-lru 删除最近最少使用的key
 *  allkeys-random 随机删除key
 *  volatile-lru 从设置了过期时间的key中删除最近很少使用的
 *  volatile-ttl 从设置了过期时间的key中删除快要过期的
 *  volatile-random 从设置了过期时间的key中随机删除
 *
 *
 *  缓存穿透
 *   使用缓存不存在的key去访问，使大量的请求到数据库
 *   解决方案：
 *   1、布隆过滤器
 *   2、互斥锁 缓存失效后 访问数据库需要加锁
 *   3、异步更新，无论是否获取到值，都直接返回。value中维护一个失效时间，缓存如果过期则异步启动一个线程去读取数据库更新缓存。
 *  缓存雪崩
 *   大量的缓存key同时失效，数据库连接过多
 *   解决方案：
 *   1、给缓存时间加随机值，避免集体失效
 *   2、双缓存，A有失效时间 B不设置失效时间
 *
 *  并发竞争key
 *   如果不要求顺序，则使用分布式锁
 *   如果要求顺序，可以用时间戳来判断，或者使用队列的方式set
 *
 *  数据库缓存一致性问题
 *
 * docker run -p 6300:6379 --name redis-master -v ~/docker/conf/redis-master.conf:/usr/local/etc/redis/redis.conf -d redis redis-server
 * 使用场景
 * 实现原理
 *
 *  rehash
 *    https://www.cnblogs.com/williamjie/p/11205593.html
 *    维护两个数组，ht[0],ht[1]
 *    ht[1]扩容，每次访问ht[0]的key时，将key hash到对应的ht[1]里
 *    如果新增一个key则直接在ht[1]里新增
 *
 *  跳表
 *    https://www.jianshu.com/p/61f8cad04177
 *    sorted set的实现
 *    由多个层级的链表构成，循环查询对应的值
 *
 *  redis cluster
 *    https://blog.csdn.net/fouy_yun/article/details/81590252#3%E4%B8%80%E8%87%B4%E6%80%A7%E7%9A%84%E8%BE%BE%E6%88%90
 *
 *  redis 哨兵
 *    https://www.cnblogs.com/kevingrace/p/9004460.html
 *
 */
public class RedisDoc {


}
