package com.example.demo.future.lock;

/**
 * ========================================================================================================================
 * 编译后查看volatile修饰的字节码
 * Access flags : static volatile
 *
 * jvm层面生成了lock cmpxchg %rdi,(%rdx)，一个lock前缀的额指令，
 * 当前处理器的缓存行数据写回主内容，其他处理器缓存的数据失效（总线嗅探）
 *
 * MESI 缓存一致性协议 M修改 E独享 S共享 I失效，只有一个cpu使用了缓存行，E，多个使用缓存行 S，有一个cpu更改了缓存行M需要写回主从，变为I
 *
 *
 * =======================================================================================================================
 * 以上为volatile的可见性简单分析
 *
 * volatile 的有序性分析
 *
 * volatile - happens-before  对于一个volatile的写happens-before任意后续对他的读
 *
 *
 * 以下为volatile的防止指令重排（内存屏障）
 *
 *
 */
public class VolatileTest {
    static volatile int i = 0;

    public static void main(String[] args) {
        System.out.println(i);
    }
}

class V {

    //在每个volatile写操作前插入一个StoreStore屏障，禁止上面的普通写和volatile写重排序
    //在每个volatile写操作后面插入一个StoreLoad屏障， 禁止上面的volatile写与下面的volatile读写重排

    static volatile boolean result = false;

    int i = 0;

    public void test(){
        i = 1;
        //
        result = true;
    }
}