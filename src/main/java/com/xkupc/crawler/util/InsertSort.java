package com.xkupc.crawler.util;

/**
 * @author xk
 * @createTime 2017/12/6 0006 下午 7:02
 * @description 简单版插入排序
 */
public class InsertSort {

    public int[] insertSort(int[] num, int left, int right) {
        for (int i = left; i < right; i++) {
            for (int j = i; j > left; j--) {
                if (num[j - 1] > num[j]) {
                    swapNum(num, j - 1, j);
                }
            }
        }
        return num;
    }

    private void swapNum(int[] num, int i, int j) {
        int temp = num[j];
        num[j] = num[i];
        num[i] = temp;
    }

    public static void main(String[] args){
        InsertSort insertSort = new InsertSort();
        int[] num = new int[]{6,3,5,2,1,7};
        insertSort.insertSort(num,0,num.length);
    }
}
