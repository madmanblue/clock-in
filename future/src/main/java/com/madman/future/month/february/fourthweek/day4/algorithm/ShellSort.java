package com.madman.future.month.february.fourthweek.day4.algorithm;

import com.madman.future.common.AlgorithmUtil;

public class ShellSort {

    public static void main(String[] args) {
        int[] arr = AlgorithmUtil.initArr(10);
        AlgorithmUtil.printArr(arr);
        shellSort(arr);
        AlgorithmUtil.printArr(arr);

    }

    public static void shellSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

    }
}
