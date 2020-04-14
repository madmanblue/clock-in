package com.example.demo.future.algorithm.offer;


import java.util.Stack;

public class Stack2Queue {
    Stack<Integer> queue , tmp;

    public Stack2Queue() {
        queue = new Stack<>();
        tmp = new Stack<>();
    }

    public void addTail(Integer i) {
        queue.push(i);
    }

    public Integer deleteHead() {
        while (!queue.empty()) {
            tmp.push(queue.pop());
        }
        Integer i = null;
        if (tmp.empty()) {
            return -1;
        } else {
            i = tmp.pop();
            while (!tmp.empty()) {
                queue.push(tmp.pop());
            }
            return i;
        }
    }
}
