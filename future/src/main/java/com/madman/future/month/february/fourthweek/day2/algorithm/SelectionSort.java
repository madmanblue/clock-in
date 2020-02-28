package com.madman.future.month.february.fourthweek.day2.algorithm;

import com.madman.future.common.AlgorithmUtil;

import java.util.Objects;

/**
 * 选择排序
 * 
 */
public class SelectionSort {
    public static void main(String[] args) {
        SelectionSortDemo.sort(Objects.requireNonNull(AlgorithmUtil.initArr(15)));
    }
}

class SelectionSortDemo {

    public static void sort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        test(arr);
    }

    public static void test(int[] arr) {
        AlgorithmUtil.printArr(arr);
        // 先找当前最小的值
        int minIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;

        }

        System.out.println("min index : " + minIndex);
        AlgorithmUtil.printArr(arr);
    }
}
