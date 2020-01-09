package com.madman.doc.distributed;

/**
 * FastDFS介绍及简单原理
 *
 *  组成：
 *   1.client 客户端 发起请求到跟踪服务器或存储节点进行对应的操作
 *   2.tracker 跟踪服务器 只要做调度工作，负责管理storage服务器和group，每个storage服务器启动后连接tracker，通知自己对应的group，并
 *      周期保持心跳，根据心跳信息建立映射表，保存在内存中。扩容的话，新增机器并将对应的storage的配置更改，新增扩容机器地址和端口。
 *   3.storage 存储节点 以group为单位，每个group包含多个storage服务器。数据护互为备份，存储空间以容量最小的服务器为准。
 *      文件目录分两级，每级256个文件夹，所以存储的节点有256*256
 *
 *  文件上传流程：
 *      1.fastDFS集群启动后，storage向tracker发送心跳，初始化group-》storage server缓存
 *      2.客户端发起上传请求，访问tracker
 *      3.当tracker接收到上传请求后，会为该文件分配一个可以上传的group，支持 Round Robin （轮询）、Specified robin（指定）、Load
 *        balance（剩余存储多的节点优先），分配group后选择对应group下的storage server，选择支持 Round Robin （轮询）、按ip排序、
 *        按优先级排序（对应的配置文件配置）
 *      4.收到storage server信息后，客户端发起写请求，storage server将分配一个文件存储目录，支持多存储目录轮询、剩余空间最多优先
 *      5.选定存储目录后，会生成一个fileid，由storage ip、文件创建时间、文件大小、文件crc32和一个随机数拼接而成，让后将这个二进制串
 *        进行base64编码，转换为可打印的字符串。
 *      6.根据fileed选择两级目录，然后以fileid为文件名存储到目录
 *      7.存储成功后，返回文件名即路径 （group + 存储目录 + 两级子目录 + fileid + 文件后缀）
 *        eg:f2/user/g9/M00/7A/4B/rBVaVV4UAE6ATNVsACp3BJFBSDQ866.txt
 *add
 *
 *  文件同步：
 *      1.文件上传成功后会由后台线程同步至同group下其他storage server
 *      2.同步时间戳（关于tracker获取同步进度的情况，以时间小的为准）
 *
 *  文件下载：
 *      1.fastDFS发起下载请求
 *      2.tracker解析group、文件大小、创建时间信息，然后选中一个storage server并返回，用于读请求。
 *      3.如果对应的storage机器同步有延迟，没有找到文件，，我们使用nginx_fastdfs_module解决问题。
 */
public class FastDFS {
}
