package com.future.api;

import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.proto.WatcherEvent;

import java.util.concurrent.CountDownLatch;

public class ZookeeperApi {

    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.127.132:2181,192.168.127.133:2181,192.168.127.134:2181,192.168.127.135:2181",
                3000, new Watcher() {
            public void process(WatchedEvent event) {
                KeeperState state = event.getState();
                Event.EventType type = event.getType();
                String path = event.getPath();

                System.out.println(event.toString());

                WatcherEvent wrapper = event.getWrapper();
                switch (state) {
                    case Unknown:
                        break;
                    case Disconnected:
                        System.out.println("Disconnected");
                        break;
                    case NoSyncConnected:
                        break;
                    case SyncConnected:
                        countDownLatch.countDown();
                        break;
                    case AuthFailed:
                        break;
                    case ConnectedReadOnly:
                        break;
                    case SaslAuthenticated:
                        break;
                    case Expired:
                        break;
                    case Closed:
                        break;

                    default:
                        throw new RuntimeException("Invalid integer value for conversion to KeeperState");
                }
            }
        });

        countDownLatch.await();

        String pathName = zooKeeper.create("/sjf", "wolaila".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        byte[] data = zooKeeper.getData("/sjf", new Watcher() {
            public void process(WatchedEvent event) {
                KeeperState state = event.getState();
                System.out.println("getData : " + state);
            }
        }, new Stat());
        System.out.println(new String(data));
        Stat stat = zooKeeper.setData("/sjf", "wozoule".getBytes(), 0);
        Stat stat1 = zooKeeper.setData("/sjf", "lalala".getBytes(), 1);
    }
}