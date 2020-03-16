package com.madman.doc.jvm;

/**
 * volatile 修饰的变量字节码层面 被acc_volatile修饰  jvm层面是通过加内存屏障指令
 *
 * synchronized 字节码monitorenter monitorexit 对象头
 *
 * jvm由类加载器，运行时数据区，执行引擎，
 *
 * 类加载器：分为启动类加载器，扩展类加载器，应用类加载器，自定义类加载器
 *
 * 栈上分配：小对象，没有逃逸，好处是栈中分配速度快，方法调用完毕，对象被回收，不用等GC
 * TLAB分配：多线程环境分配内存需要争抢，在线程私有的空间里分配可以免争抢更快
 *
 *
 * 垃圾回收算法：
 *  copy：复制算法，将一块内存分成两块一样大小的内存，将不能回收的对象复制到另外一块中
 *  好处是速度快，没有内存碎片，适用于回收对象很多的情况，使用在新生代
 *  mark-sweep：标记清除算法，
 *
 *  mark-compact：标记整理算法：
 */
public class JVM {
    volatile int a ;
    synchronized void m(){}

    void n(){
        synchronized (this){

        }
    }

    public static void main(String[] args) {

    }
}
