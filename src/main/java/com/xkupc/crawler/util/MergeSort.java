package com.xkupc.crawler.util;

/**
 * @author xk
 * @createTime 2017/12/2 0002 下午 8:22
 * @description 归并排序
 */
public class MergeSort {

    public int[] megerSort(int[] num, int left, int right) {
        if (left < right) {
            int baseIndex = (left + right) / 2;
            megerSort(num, left, baseIndex);
            megerSort(num, baseIndex + 1, right);
            merge(num, left, baseIndex, right);
        }
        return num;
    }

    private void merge(int[] num, int left, int baseIndex, int right) {
        //初始化两个临时有序序列
        int[] leftNum = new int[baseIndex - left + 1 + 1];

        for (int i = 0; i < baseIndex - left + 1; i++) {
            leftNum[i] = num[left + i];
        }
        leftNum[baseIndex - left + 1] = Integer.MAX_VALUE;
        int[] rightNum = new int[right - baseIndex + 1];
        for (int j = 0; j < right - baseIndex; j++) {
            rightNum[j] = num[baseIndex + j + 1];
        }
        rightNum[right - baseIndex] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k = left; k <= right; k++) {
            if (leftNum[i] < rightNum[j]) {
                num[k] = leftNum[i];
                i++;
            } else {
                num[k] = rightNum[j];
                j++;
            }
        }
    }

    public static void main(String[] arg) {
        MergeSort mergeSort = new MergeSort();
        int[] num = new int[]{6, 7, 7, 5, 10, 2, 8, 2, 9, 1, 3};
        num = mergeSort.megerSort(num, 0, num.length - 1);
    }
}
