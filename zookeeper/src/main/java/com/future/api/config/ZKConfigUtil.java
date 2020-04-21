package com.future.api.config;

import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class ZKConfigUtil {

    private static ZooKeeper zk;

    //zk主要节点路径
    private static final String zkAddr = "192.168.127.132:2181,192.168.127.133:2181,192.168.127.134:2181,192.168.127.135:2181/config";

    public static ZooKeeper getZookeeper(){
        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            DefaultWatcher defaultWatcher = new DefaultWatcher();
            defaultWatcher.setCountDownLatch(countDownLatch);
            zk = new ZooKeeper(zkAddr, 10000, defaultWatcher);
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zk;
    }
}
