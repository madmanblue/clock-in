package com.madman.doc.algorithm;

public class Sort {

    // 冒泡排序
    public static void bubbleSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 9, 2, 1, 7};
        ArrayUtil.printArr(arr);
        // bubbleSort(arr);
        // selectSort(arr);
        insertionSort(arr);
        ArrayUtil.printArr(arr);
    }

    // 选择排序
    public static void selectSort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int tmp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = tmp;
            }
        }
    }

    public static void selectionSort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {

            }
        }
    }

    // 插入排序
    public static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(String.format("i : %s  ", i));
            ArrayUtil.printArr(arr);
            for (int j = i + 1; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                } else {
                    System.out.println("不需要交换");
                    break;
                }
                System.out.print(String.format("i : %s -- ", i));
                System.out.print(String.format("j : %s  ", j));
                ArrayUtil.printArr(arr);
            }
        }
    }

}

class ArrayUtil {
    public static void printArr(int[] arr) {
        if (null == arr) {
            System.out.println("");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i : arr) {
            sb.append(i).append(",");
        }
        System.out.println(sb.toString());
    }
}