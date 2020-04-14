# Mysql
---
1、Mysql的索引类型，底层索引数据结构，叶子节点存储的是什么，索引失效的原因  
   主键索引，唯一索引，普通索引，全文索引  
   索引是通过B+树实现的，MyISAM的索引中，节点存储了数据记录的地址  
   InnoDB索引中叶子节点保存的是主键id，如果是普通索引，则查询数据时
   需要回表
---
2、如何优化sql，查询计划的结果中看哪些些关键数据

---
3、innodb和myisam的区别  
    InnoDB支持事务，表锁，行锁。如果表未建立索引
    则行锁失效，只能建立的索引上。
    MyISAM不支持事务，支持表锁
---  
4、mysql默认隔离级别，
    读未提交，读已提交，可重复读，串行化  
    默认可重复读
---
5、mysql的乐观锁和悲观锁，锁的种类
    乐观锁MVCC多版本并发控制  
    悲观锁，表锁或者行锁  
    读写锁，读锁可以加读锁，写锁什么锁都不加
---
6、如何用sql实现乐观锁和悲观锁  
    ```
    select * from user where userid = 1 for update;
    update user set name=1 where id=1 and version = 90;   
    ```
---
7、mysql如何分库分表  
表的拆分：垂直分表-将变动频繁的字段写到一起，将不频繁的写到一起
，水平分表-切割单表数量，可以通过hash或者分区
---
8、MySQL为什么选择B+树作为它的存储结构，为什么不选择Hash、二叉、红黑树
hash索引会有hash冲突，而且无法进行范围查找  
二叉树和红黑树会使深度变深，查询困难
---
9、Mysql数据库的事务与锁的理解

---
10、数据库临时表有没有用过，是怎么用的？

---
11、多数据源情况下如何进行事物的管理  
需要分布式事务管理  
---
12、Union和union all有什么区别
union去重后合并，union all未去重合并
---
13、dateTime和timestamp有什么区别

---
14、mysql主从模式的实现    
    主节点写binlog日志后，起一个dump线程发送到从节点，
    从节点起两个线程，一个是io线程，将dump的数据写入
    relaylog中，另一个是sql线程，将relaylog中的操作
    重放到从库  
---
15、如何解析sql语句；即explain关键字的使用  
    explain 可以看的字段有type（all全表扫描，index全索引扫描，range范围查询
    ref非唯一索引，eq_ref唯一索引，conts主键索引，system系统表），
    possiblekey，keylength,ref 索引列，rows 查到的数据，extra额外说名，using index  
---
16、Mysql的主从同步原理，mysql主从复制主要有几种模式