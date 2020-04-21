### Zookeeper
* paxos
* zab
* leader follower observer
* 配置中心  
    通过事件回调机制，然后不停的更新对应的数据  
* 分布式锁  
    在同一个节点下，写入临时顺序节点，判断当前第一个节点是否是自己
    如果是用countDownLatch-1，如果不是，调用exists前一个节点
    监听是否被删除