package com.future.api.lock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkLock implements Watcher, AsyncCallback.DataCallback, AsyncCallback.StatCallback, AsyncCallback.Children2Callback,AsyncCallback.StringCallback {

    //节点更改回调
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {

    }

    //状态回调
    public void processResult(int rc, String path, Object ctx, Stat stat) {

    }

    //监视器检测
    public void process(WatchedEvent event) {
        switch (event.getType()) {

            case None:
                break;
            case NodeCreated:
                break;
            case NodeDeleted:
                //为了回调
                zooKeeper.getChildren("/", false, this, new Object());
                break;
            case NodeDataChanged:
                break;
            case NodeChildrenChanged:
                break;
            case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;
        }
    }

    private ZooKeeper zooKeeper;
    public void setZk(ZooKeeper zookeeper) {
        this.zooKeeper = zookeeper;
    }

    private String threadName;
    public void setThreadName(String name) {
        this.threadName = name;
    }

    CountDownLatch countDownLatch = new CountDownLatch(1);
    public void tryLock() {

        //判断当前节点下是否是自己，如果不是，创建节点并监听前一节点,create回调StringCallBack
        zooKeeper.create("/lock", threadName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, this, new Object());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void unLock() {
        try {
            zooKeeper.delete(pathName, -1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    //获取子节点回调
    public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
//        children.forEach(System.out::print);

        Collections.sort(children);

        int i = children.indexOf(pathName.substring(1));

        if (i == 0) {

            System.out.println(threadName + " 第一个 锁 ");
            countDownLatch.countDown();
        } else {
            zooKeeper.exists("/" + children.get(i - 1 ), this, this, new Object());
        }
    }
    private String pathName;

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    //StringCallback
    public void processResult(int rc, String path, Object ctx, String name) {
        if (null != name) {
            System.out.println(threadName + " ->>  " + name);
            setPathName(name);
            //判断当前目录下是否是最小的
            zooKeeper.getChildren("/", false, this, new Object());
        }
    }
}
