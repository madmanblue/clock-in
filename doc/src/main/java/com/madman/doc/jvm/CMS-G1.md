## CMS - G1

* CMS 
    - 老年代回收器，采用标记整理算法，不会产生内存碎片，在垃圾回收过程中
    提高了响应时间，降低了吞吐量，适合电商这种要求响应时间的应用。  
    - 清理过程  
        - 初始标记：程序到达程序安全点，STW，标记GCRoots下的节点， GCRoots
        有栈帧中引用的对象，类的静态变量，运行时常量池中对象，JNI指针  
        - 并发标记：工作线程和垃圾回收线程工作  
        - 重复标记：STW，重新标记之前并发过程中的垃圾  
        - 并发清理：工作线程和垃圾回收线程工作，可能会产生浮动垃圾，等待下次回收  

* G1
    - 并发收集  
    - 压缩空闲空间不会延长GC的暂停时间  
    - 更易于预测GC暂停时间  
    - 适用于不要实现很高吞吐量的场景  
    - 合适触发 ： 空间不足以分配大的内存  
    - XX:InitiatingHeapOccupacyPercent  
      - 默认值45%
      - 当老年代超过这个值启动MixedGC  
          - MixedGC过程与CMS很一致，可以将MixedGC提前执行，将默认值调低
* Card Table
    - 解决了新生代的GCRoots已经到了老年代的问题，因为YGC会扫描老年代  
    - 将对象分块，如果有指向新生代的块，会被标记为Dirty，扫描只需要扫描Dirty
    的Card Table，使用BitMap实现。
    
* Collection Set (CSet)  
    - 放着Card Table需要回收的数据

* Remembered Set (RSet)                    
    - 记录了其他Region中的对象到本Region的引用  。
    - RSet的作用在于，使得垃圾回收器不需要扫描整个堆找到谁引用了当前分区中的对象，只是需要扫描RSet即可。  
    
    
* 并发标记的算法  
    - 三色标记  
        - 白色：未标记的对象  
        - 灰色：本身被标记，成员变量未被标记  
        - 黑色：所有的对象都已经被标记

    - 漏标  
        - 在并发标记过程中有一个黑色的对象指向了白色对象，然后灰色指向白色的引用断开，此时对象漏标
        - 解决方式  
            - 增量更新，将黑色重新标记为灰色，下次重新扫描  （CMS）
            - SATB，关注引用的删除，当灰色到白色的引用被删除时，将引用推到GC的堆栈，保证GC扫描 （G1）
        - G1采用SATB的方式是为了防止重新扫描，需要配合RSet查找当前哪些对象指向了自己。
        
        
 * G1我自己的理解
    - 逻辑分代：新生代（eden，survive）、老年代、大内存空间
    - 避免出现fullGC，可以通过硬件升级或者将MixedGC触发时间提前
    - 并发标记时采用的算法  
        - 三色标记  
            - 黑：当前对象已经被扫描 灰：当前已经被扫描，引用的对象未扫描 白：未被扫描
            - 漏标问题  
                - CMS采用增量更新，重置黑色节点颜色为灰色，问题是需要重新扫描
                - SATB采用指针推送方式，如果灰色节点将指针取消，则需要把指针推送到GC堆栈，标记白色节点
    - Card Table  
    - Collection Set  
    - Remembered Set              


* GC常用的参数
    - -Xmn -Xms -Xmx -Xss:年轻代 最小堆 最大堆 栈
    - -XX:+PrintGCDetails 打印GC详情
    - -XX:+PrintFlagsFinal -version | grep '' 
    - -Xloggc:opt/log/gc.log
    - -XX:MaxTenuringThreshold 升代年龄 最大值15
    - CMS  
        - java -XX:+UseConcMarkSweepGC 使用CMS垃圾回收            