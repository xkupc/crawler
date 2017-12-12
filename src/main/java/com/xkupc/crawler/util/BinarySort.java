package com.xkupc.crawler.util;


import java.util.Arrays;

/**
 * @author xk
 * @createTime 2017/12/12 0012 上午 11:55
 * @description 插入排序
 */
public class BinarySort {

    private int[] binarySort(int[] num, int left, int right, int start) {
        if (left == start) {
            start++;
        }
        for (; start < right; start++) {
            int pivot = num[start];
            int l = left;
            int r = start;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (num[mid] > pivot) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            assert l == r;
            int n = start - l;
            //插入排序，从插入点开始，将后面的元素后移
            switch (n) {
                case 2:
                    num[l + 2] = num[l + 1];
                case 1:
                    num[l + 1] = num[l];
                    break;
                default:
                    System.arraycopy(num, l, num, l + 1, n);
            }
            num[l] = pivot;
        }
        return num;
    }

    public static void main(String[] args){
        BinarySort binarySort = new BinarySort();
        Integer[] num = new Integer[]{30,19,6,23,14,3,22,15,13,21,5,12,28,2,18,24,1,16,29,7,11,25,4,17,9,26,8,20,10,27,31,32,33};
        //binarySort.binarySort(num,0,num.length,0);
        Arrays.sort(num);
    }
}
