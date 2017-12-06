package com.xkupc.crawler.util;

/**
 * @author xk
 * @createTime 2017/12/6 0006 下午 6:53
 * @description 初步优化的归并算法
 */
public class LegacyMergeSort {
    private static final int INSERTSORT_THRESHOLD = 7;

    public int[] legacyMergeSore(int[] num, int left, int right) {
        //拷贝目标数组
        int[] cloneNum = num.clone();
        return mergeSort(cloneNum, num, left, right);
    }

    private int[] mergeSort(int[] cloneNum, int[] num, int left, int right) {
        int length = right - left;
        if (length < INSERTSORT_THRESHOLD) {
            //采用插入排序算法
            InsertSort insertSort = new InsertSort();
            insertSort.insertSort(num, left, right);
            return num;
        }
        int baseIndex = (left + right) >>> 1;
        mergeSort(num, cloneNum, left, baseIndex);
        mergeSort(num, cloneNum, baseIndex, right);
        merge(num, cloneNum, left, baseIndex, right);
        return num;
    }

    /**
     * 合并序列
     *
     * @param num
     * @param cloneNum
     * @param left
     * @param baseIndex
     * @param right
     */
    private void merge(int[] num, int[] cloneNum, int left, int baseIndex, int right) {
        //校验是否有序，因为baseIndex为第二个子序列的起始值，且两个子序列已有序
        if (cloneNum[baseIndex - 1] <= cloneNum[baseIndex]) {
            //两子序列之间已有序，直接拷贝到目标序列
            for (int i = left; i < right; i++) {
                num[i] = cloneNum[i];
            }
            return;
        }
        for (int j = left, p = left, q = baseIndex; j < right; j++) {
            if (q >= right || p < baseIndex && cloneNum[p] < cloneNum[q]) {
                num[j] = cloneNum[p++];
            } else {
                num[j] = cloneNum[q++];
            }
        }
    }

    public static void main(String[] args) {
        LegacyMergeSort legacyMergeSort = new LegacyMergeSort();
        int[] num = new int[]{6, 7, 7, 5, 10, 2, 8, 2, 9, 1, 3};
        legacyMergeSort.legacyMergeSore(num,0,num.length);
    }
}
