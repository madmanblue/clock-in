package com.madman.future.month.february.fourthweek.day1.designpatterns;

/**
 * 单例模式：确保一个类只有一个实例，而且自行实例化并向整个系统提供这个实例。
 */
public class SingletonPattern {

    public static void main(String[] args) {
        SingletonDemo singletonDemo = SingletonDemo.getInstance();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(singletonDemo.hashCode());
            }).start();
        }

    }

}

/**
 * 饿汉 未使用直接装配
 */
class SingletonDemoHungry {

    private static final SingletonDemoHungry instance = new SingletonDemoHungry();

    private SingletonDemoHungry() {

    }

    public SingletonDemoHungry getInstance() {
        return instance;
    }
}

/**
 * 懒汉 双检锁
 * 
 * 实例非空判断耗费资源
 */
class SingletonDemo {

    // 防止指令冲排序时，返回null，看jvm类加载过程
    private static volatile SingletonDemo INSTANCE = null;

    private SingletonDemo() {}

    public static SingletonDemo getInstance() {

        if (null == INSTANCE) {
            synchronized (SingletonDemo.class) {
                if (null == INSTANCE) {
                    INSTANCE = new SingletonDemo();
                }
            }
        }
        return INSTANCE;
    }
}

/**
 * 静态内部类
 * 
 */
class SingletonDemoStatic {

    private SingletonDemoStatic() {}

    private static class Holder {
        private static SingletonDemoStatic instance = new SingletonDemoStatic();
    }

    public static SingletonDemoStatic getInstance() {
        return Holder.instance;
    }
}

/**
 * 枚举类
 */
enum SingletonDemoEnum {
    INSTANCE;

    public static SingletonDemoEnum getInstance() {
        return INSTANCE;
    }
}