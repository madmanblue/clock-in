package com.madman.future.month.february.fourthweek.day1.algorithm;

import com.madman.future.common.AlgorithmUtil;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = AlgorithmUtil.initArr(10);
        AlgorithmUtil.printArr(arr);
        bubbleSort(arr);
        AlgorithmUtil.printArr(arr);

    }

    private static void bubbleSort(int[] arr) {
        if (null == arr || arr.length == 0) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    private static void bubbleSortPlus(int[] arr) {
        if (null == arr || arr.length == 0) {

            return;
        }

        for (int i = 0; i < arr.length; i++) {// 控制循环的次数
            boolean change = true;

            for (int j = 0; j < arr.length - 1 - i; j++) { // 由于每次执行后，最后的数都是已经排序过的，故不用循环对比
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    change = false;
                }
            }
            if (change) {// 如果第二层循环未改动，则说明后面的已经全部排序完成了
                break;
            }
        }
    }
}
