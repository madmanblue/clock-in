package com.madman.future.month.february.fourthweek.day3.algorithm;

import com.madman.future.common.AlgorithmUtil;

/**
 * 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = AlgorithmUtil.initArr(5);
        AlgorithmUtil.printArr(arr);
        insertionSort(arr);
        AlgorithmUtil.printArr(arr);
    }

    public static void insertionSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }

        // 往前对比，如果比前面的小，就继续往前比，然后将当前大的往后移
        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            int lastIndex = i - 1;
            while (lastIndex >= 0 && current < arr[lastIndex]) {
                arr[lastIndex + 1] = arr[lastIndex];
                lastIndex--;
            }
            arr[lastIndex + 1] = current;
        }
    }
}
