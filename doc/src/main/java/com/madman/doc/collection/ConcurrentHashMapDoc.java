package com.madman.doc.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 1.7
 *
 * 1.8
 *
 *
 */
public class ConcurrentHashMapDoc {
    public static int HASH_BITS = 0x7fffffff;
    public static void main(String[] args) throws InterruptedException {
        testHash();
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("test", new Object());
        Object test = concurrentHashMap.get("test");
        LinkedList<Object> list = new LinkedList<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        print("wandan ");
        while (true){
            System.out.println("create ->" + new Date());
            list.addFirst(test);
            System.out.println(list.size());
            arrayList.add(test);
            System.out.println(arrayList.size());
        }

    }
    public static String print(String args){
        return print(args);
    }
    public static void testHash(){
        String key = "test1";
        int hash = spread(key.hashCode());
        System.out.println(hash);
    }

    public static int spread(int h){
        return (h ^ (h >>> 16)) & HASH_BITS;
    }
}
