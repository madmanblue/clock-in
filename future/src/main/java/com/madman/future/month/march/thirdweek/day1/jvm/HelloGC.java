package com.madman.future.month.march.thirdweek.day1.jvm;

public class HelloGC {

    public static void main(String[] args) {
        System.out.println("Hello GC");
        for (int i = 0; i < 10_000; i++) {
            byte[] b = new byte[1024 * 1024];
        }
    }
}
