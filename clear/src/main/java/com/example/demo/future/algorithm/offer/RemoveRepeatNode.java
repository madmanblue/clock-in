package com.example.demo.future.algorithm.offer;

import java.util.HashSet;
import java.util.Set;

/**
 * 未实现
 */
public class RemoveRepeatNode {

    public static void main(String[] args) {

    }

    public static ListNode remove(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode n = head;
        while (head != null && head.next != null) {
            ListNode tmp = head;
            while (tmp != null && tmp.next != null) {
                while (tmp.next != null && head.val == tmp.next.val) {
                    tmp.next = tmp.next.next;
                }
                tmp = tmp.next;
            }
            head = head.next;
        }
        return n;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
