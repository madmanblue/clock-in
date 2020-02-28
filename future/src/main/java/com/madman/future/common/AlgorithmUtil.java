package com.madman.future.common;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Random;

/**
 * 算法工具类
 */
@Slf4j
public class AlgorithmUtil {
    public static void main(String[] args) {
        int[] integers = initArr(10);
        for (int integer : integers) {
            log.error(integer + " ");
        }
    }

    public static int[] initArr(int length) {
        if (length > 0) {
            int[] arr = new int[length];
            Random random = new Random(10);
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i = 0; i < length; i++) {

                int randomInt = random.nextInt(10);
                if (arrayList.contains(randomInt)) {
                    i--;
                } else {
                    arrayList.add(randomInt);
                }

            }
            for (int i = 0; i < arrayList.size(); i++) {
                arr[i] = arrayList.get(i);
            }
            return arr;
        }
        return null;
    }

    public static void printArr(int[] arr) {
        if (null == arr || arr.length == 0) {
            log.error("current arr is null");
            return;
        }
        StringBuilder sb = new StringBuilder("arr is : ");
        for (int i : arr) {
            sb.append(" ").append(i);
        }
        log.error(sb.toString());
    }
}
