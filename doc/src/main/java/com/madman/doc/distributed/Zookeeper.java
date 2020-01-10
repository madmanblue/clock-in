package com.madman.doc.distributed;

/**
 * zookeeper介绍，原理，使用
 * zookeeper是什么？
 *  一个分布式的应用程序协调服务。提供了文件系统和通知机制。
 *
 * zookeeper文件系统？
 *  提供了一个多节点的命名空间，节点称为znode，与文件系统不同的是，这些节点都可以关联数据，而文件系统中目录是无法关联数据的。
 *  为了保证高吞吐量和低延迟，在内存中维护这个树状结构，所以zookeeper无法存放大数据，每个节点存放数据上限1M。
 *
 * zookeeper节点类型
 *  1.persistent 持久化节点
 *      客户端断开连接，该节点仍然存在
 *  2.persistent_sequential 持久化顺序节点
 *      客户端断开连接，该节点仍然存在，只是zookeeper给节点都进行了顺序编号
 *  3.ephemeral 临时节点
 *      客户端断开连接，该节点被删除
 *  4.ephemeral_sequential 临时顺序节点
 *      客户端断开连接，该节点被删除，只是zookeeper给节点都进行了顺序编号
 *
 * zookeeper通知机制
 *  client会对znode建立一个watcher事件，当该znode发生变化，这个client就会收到zk的通知，然后根据zk的变化做出业务上的改变
 *
 *
 * zookeeper做了什么
 *  命名服务
 *  配置管理
 *  集群管理
 *  分布式锁
 *  队列管理
 *
 * zookeeper配置管理
 *  将程序的配置写入znode下，当配置发生改变时，利用watcher机制通知给各客户端，从而更改配置
 *
 * zookeeper集群管理
 *  主要是节点的变动以及选举master
 *  也是通过watcher机制监控节点状态，通过创建临时节点，如果服务宕机，则对应的节点被删除，集群都能收到通知
 *  选举的话，可以创建临时顺序节点，每次选目录节点最小的机器为master
 *
 * 脑裂
 *  一个集群部署在两个机房，当机房之间的网络断开，则选举出两个leader
 *  解决办法：过半机制，如果当前投票的机器大于全部机器的一半就可以，好处是快速选举防止脑裂
 *
 * zookeeper分布式锁
 *  两种锁
 *   独占锁 ： 将一个节点看做一个锁，所有客户端调用createNode方法创建/someoneLock，创建成功则获取到锁，解锁需要删除节点
 *   控制时序：在一个节点上创建临时顺序节点，编号最小的获取锁，解锁则删除
 *
 *
 * zookeeper队列管理
 *  同步队列：监控节点下的子节点数量是否满足条件，如果不满足则等待，如果满足则释放
 *  FIFO队列：在指定节点下创建持久顺序节点，节点删除目录最小节点作为消费，因为持久节点，所以不存在消息丢失的问题
 *
 *
 * zookeeper数据复制
 *  写任意，observer
 *  响应能力建立在是延迟复制保持最终一致还是立即复制快速响应
 *
 *
 * zookeeper工作原理
 *  核心是原子广播，这个机制保证了各个server之间的状态一致，而广播使用的协议交Zab协议。
 *  Zab协议有两种模式：恢复模式 （选主） 广播 （同步）
 *  当系统 启动或崩溃时，进入恢复模式，选出leader后，且大多数server完成了和leader的同步，恢复模式结束。状态同步
 *  保证了leader和server具有相同的系统状态。
 *
 *
 * zookeeper的工作状态
 *  looking：当前server不知道谁是leader，选举状态
 *  leading：当前server是leader
 *  following：leader选举出来以后与当前server同步，随从状态，同步leader，参与投票
 *  observing：同步leader状态，不参与投票
 *
 * zookeeper选主
 *  开始选举阶段，先获取自己的zxid
 *  第一轮投票都投给自己，投票信息包含：所选举的serverid，zxid，epoch，epoch随着选举的增加而递增
 *  接收到投票信息后
 *    首先判断epoch的值，
 *     如果收到的epoch比自己的大，更新自己的epoch，同时清空本轮逻辑时钟收集到的来自其他server的
 *     选举数据。然后判断是否需要更新当前自己的选举leader serverid，判断规则是，先看zxid的大小，在判断leader serverid
 *     ，然后将自身最新的选举结果广播，即 leaderserverid， zxid， epoch
 *     如果收到的epoch小与当前的epoch，直接将当前的leader serverid ， zxid， epoch发过去
 *     如果收到的epoch相等，判断zxid和leader serverid的大小，然后再将数据广播
 *    然后判断是否收到了所有的投票
 *     如果是，判断当前的节点是leader还是follower
 *     如果不是，判断自己选举的leader是不是得到超过半数以上的投票，如果是，尝试在200ms接受下数据，如果没有收到
 *     ，说明已经选出leader ， 设置当前节点为follower
 *
 *
 *
 *
 * zookeeper同步
 *
 * zookeeper保证事务顺序一致
 *  zookeeper采用递增的事务id来标识，所有的proposal提议都在提出的时候加上了zxid。
 *  zxid实际上是一个64位的数字，高32位是epoch，用来标识leader，如果有新leader被选举出来epoch会递增，低32位用来递增计数
 *  当新生成proposal时，会向其他的server发出事务执行请求，如果超过半数的机器都能执行且成功，那么就开始执行。
 *
 *
 * zookeeper的watch机制
 *  注册watcher getData() exists() getChildren()
 *  触发watcher delete() create() setData()
 *
 *  setData成功会触发getData
 *  create触发exists，getchildren
 *  delete触发exists，getchildren
 *
 *
 *
 *
 */
public class Zookeeper {
}
