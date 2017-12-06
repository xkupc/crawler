package com.xkupc.crawler.util;

/**
 * @author xk
 * @createTime 2017/11/21 0021 下午 6:58
 * @description
 */
public class QuickSort {

    int[] quickSort(int[] num, int left, int right) {
        if (left < right) {
            int baseIndex = partiton(num, left, right);
            quickSort(num, left, baseIndex - 1);
            quickSort(num, baseIndex + 1, right);
        }
        return num;
    }

    private int partiton(int[] num, int left, int right) {
        int baseIndex = right;
        int i = left - 1;
        int j = left;
        int key = num[baseIndex];
        while (j < right) {
            if (num[j] <= key) {
                i++;
                exchange(num, i, j);
            }
            j++;
        }
        exchange(num, i + 1, baseIndex);
        return i + 1;
    }

    private void exchange(int[] num, int i, int j) {
        int temp = num[j];
        num[j] = num[i];
        num[i] = temp;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] num = new int[]{6,7,5,2,1,3};
        num = quickSort.quickSort(num, 0, num.length-1);
    }
}
