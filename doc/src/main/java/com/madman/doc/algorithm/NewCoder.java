package com.madman.doc.algorithm;

/**
 * 剑指Offer相关
 */
public class NewCoder {
    public static void main(String[] args) {
//        int target = -1;
//        int [][] arr = {{1,3,5,7},{2,4,6,8}};
//        boolean b = find(target, arr);
//        System.out.println(b);
        String s = "sl;sadfj";
        char c = s.charAt(2);
        System.out.println(c);
        char c1 = s.charAt(0);
        System.out.println(c1);
    }
    //数组相关

    /**
     * <pre>
     *     二维数组就是数组为元素的数组,int[行][列]
     *     在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
     *     每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
     *     判断数组中是否含有该整数。
     * </pre>
     */
    public static boolean find(int target, int[][] arr){
        int rows = arr.length;
        int cols = arr[0].length;
        int i = rows - 1, j = 0;

        while (i >= 0 && j < cols) {
            if (target < arr[i][j]) {
                i--;
            } else if (target > arr[i][j]) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }

}
