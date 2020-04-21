package com.future.api.config;

import org.apache.zookeeper.ZooKeeper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ZKTest {


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
//        zookeeper.getData("/user", new CustomerWatcherAndDataCallBack(), new CustomerWatcherAndDataCallBack(), new Object());
        CustomerWatcherAndDataCallBack back = new CustomerWatcherAndDataCallBack();
        MyConfig config = new MyConfig();
        back.setConfig(config);
        back.setZooKeeper(zookeeper);
        zookeeper.exists("/user", back, back, new Object());
        back.aWait();

        while (true) {
            if (null == config.getConfigStr() || config.getConfigStr().equals("")) {
                System.out.println("config miss ...");
                back.aWait();
            } else {
                System.out.println(config.getConfigStr());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
