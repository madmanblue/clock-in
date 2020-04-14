package com.madman.future.month.april._0402;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @author : blue
 * @desc :
 * @since : 2020/4/2
 */
public class Algorithm {

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int tmp;
            for (int j = arr.length - 1 - i; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    /**
     * 拿到当前的值与当前值之前的数组值做对比，直到当前值大于某个值为止，然后替换
     * 
     * @param arr
     */
    public static void insertSort(int[] arr) {
        int preIndex, current;
        for (int i = 1; i < arr.length; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    /**
     * 选择排序，找最小的下标然后将当前循环的起点替换
     * 
     * @param arr
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = i;
                if (arr[j] < arr[minIndex]) {
                    int tmp = arr[j];
                    arr[j] = arr[minIndex];
                    arr[minIndex] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 9, 8, 90, 1};
        // bubbleSort(arr);
        // insertSort(arr);
        selectSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }

        double floor = Math.floor(7 / 3);
        System.out.println(floor);
    }
}
