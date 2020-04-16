package com.example.demo.future.lock;

import lombok.Synchronized;

/**
 * 1.用处
 * 2. 原理以及优化
 *  1） java对象，对象头，（markword，指针）， 实例数据，对齐；以及锁在其中的存储
 *      2bit 锁标记 00 轻量级 01 偏向/无锁 （区别在是否是偏向锁的1bit中） 10 重量级 11 GC标记
 *      无锁 -》 偏向锁 -》轻量级 -》 重量级
 *      无锁：线程看当前是否是偏向锁，尝试将自己的线程id写入对象的对象头，写入成功后，下次再进入，判断对象头中的线程id是否为自己，如果是则获取锁。
 *      偏向锁：线程获取锁后将线程id写入对象头中，执行同步代码，另一个线程判断当前线程id是否是自己，如果不是尝试更改，更改失败后
 *      去暂停获取锁的线程并将markword指向拥有锁的线程所记录空间指针，升级为轻量级锁
 *      轻量级锁：线程将对象头markword复制到栈帧中的锁记录空间，并将记录空间的指针写入markword，，锁标志位00， 当另外的线程替换markword失败
 *      并重试，此时轻量级锁升级到重量级，锁标志位10.轻量级锁解锁过程是，将栈帧中的锁记录cas替换到markword，如果成功，则同步完成，如果失败
 *      说明升级锁，需要在释放锁的同时唤醒等待的线程。
 *      重量级锁：通过monitor对象实现，monitor关联了一个等待队列，所有获取改对象的锁线程都加入到这个队列中，只有锁释放时，notify、notifyAll
 *      所以wait和notify需要在synchronized里面
 *  2） 乐观锁，悲观锁 ： 对并发请求下的资源采取的方式不同，乐观锁认为，在更改期间不会有别的线程进行改动，只在最后执行更新时，判断版本
 *          悲观锁则相反，当前线程锁定资源，别的线程需要等待资源释放
 *  3）
 *
 *
 *
 */
public class SynchronizedTest {

    /**
     * Object wait() notify() notifyAll() equals() hashCode()
     */

    /**
     * 修饰静态方法，字节码acc_flags synchronized 修饰，对当前class对象锁定
     */
    public static synchronized void look(){

    }

    /**
     * 修饰实例方法，字节码synchronized 修饰 ，对当前实例this锁定
     */
    public synchronized void set(){

    }

    /**
     * 修饰代码块，对方法后的对象锁定， 字节码中用monitorenter和monitorexit实现锁的打开关闭
     */
    public void keep (){
        synchronized (SynchronizedTest.class) {
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T1 start ");
                synchronized (obj1) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (obj2) {
                        System.out.println("获取obj2");
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("T2 start ");
                synchronized (obj2) {

                    synchronized (obj1) {
                        System.out.println("获取obj1");
                    }
                }

            }
        }).start();
    }
}
