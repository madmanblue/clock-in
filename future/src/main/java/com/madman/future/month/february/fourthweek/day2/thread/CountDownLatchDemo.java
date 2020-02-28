package com.madman.future.month.february.fourthweek.day2.thread;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        try {

            for (int i = 0; i < 1; i++) {
                countDownLatch.countDown();
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("test end !");

    }
}
