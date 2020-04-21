package com.future.api.lock;


import com.future.api.config.ZKConfigUtil;
import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Lock {
    private ZooKeeper zookeeper;

    @Before
    public void before(){

        zookeeper = ZKConfigUtil.getZookeeper();

        System.out.println("zk开始工作=======================================");


    }

    @After
    public void close(){
        try {
            zookeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {

                    ZkLock zkLock = new ZkLock();

                    zkLock.setZk(zookeeper);

                    String name = Thread.currentThread().getName();

                    zkLock.setThreadName(name);

                    zkLock.tryLock();

                    System.out.println(name + " 开始执行业务");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    zkLock.unLock();

                }
            }).start();
        }
        while (true) {

        }
    }
}
