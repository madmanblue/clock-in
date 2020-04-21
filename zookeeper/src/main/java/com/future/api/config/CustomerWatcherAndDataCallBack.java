package com.future.api.config;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class CustomerWatcherAndDataCallBack implements Watcher, AsyncCallback.DataCallback, AsyncCallback.StatCallback {

    private ZooKeeper zooKeeper;

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    MyConfig config;

    public void setConfig(MyConfig config) {
        this.config = config;
    }

    //节点回调方法
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println("这是节点数据变动后回调的方方法 ： " + rc + ", " + path + ", " + ctx + "," + data + "," + stat);

        if (null != data) {
            config.setConfigStr(new String(data));
            countDownLatch.countDown();
        }
    }


    //watch注册的事件处理方法
    public void process(WatchedEvent event) {
        System.out.println("这是节点Watch回调的事件" + event.toString());

        switch (event.getType()) {
            case None:
                break;
            case NodeCreated:
                zooKeeper.getData("/user", this, this, new Object());
                break;
            case NodeDeleted:
                config.setConfigStr("");
                countDownLatch = new CountDownLatch(1);
                break;
            case NodeDataChanged:

                zooKeeper.getData("/user", this, this, new Object());
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

    //状态回调方法
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("这是状态回调的方方法 ： " + rc + ", " + path + ", " + ctx + "," + stat);
        if (null != stat) {
            zooKeeper.getData("/user", this, this, new Object());
        }
    }

    /**
     * 等待对应的配置有值
     */
    CountDownLatch countDownLatch = new CountDownLatch(1);
    public void aWait() {

        zooKeeper.exists("/user", this, this, new Object());
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
