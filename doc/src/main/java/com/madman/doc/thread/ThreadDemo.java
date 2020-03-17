package com.madman.doc.thread;

public class ThreadDemo {

  public static void main(String[] args) {
    Thread t =
        new Thread(
            () -> {
              while (Thread.currentThread().isInterrupted()) {

                String name = Thread.currentThread().getName();
                System.out.println(
                    name
                        + " is run! and thread status is "
                        + Thread.currentThread().getState()
                        + " and current interrupt status "
                        + Thread.currentThread().isInterrupted());
                try {
                  Thread.sleep(100L);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                  System.out.println(Thread.currentThread().isInterrupted());
                }
              }
            });
    t.start();
    t.interrupt();
  }
}
