package com.xkupc.crawler.util;

/**
 * @author xk
 * @createTime 2017/11/20 0020 下午 4:17
 * @description 冒泡排序
 */
public class BubbleSort {
    private Long sortNum = Long.valueOf(0);

    public int[] bubble(int num[]) {
        boolean flag = true;
        for (int i = 0; i < num.length && flag; i++) {
            flag = false;
            for (int j = num.length - 1; j > i; j--) {
                if (num[j] < num[j - 1]) {
                    int temp = num[j - 1];
                    num[j - 1] = num[j];
                    num[j] = temp;
                    sortNum++;
                    flag = true;
                }
            }
        }
        return num;
    }

    public Long getSortNum() {
        return sortNum;
    }

    public void setSortNum(Long sortNum) {
        this.sortNum = sortNum;
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] num = bubbleSort.bubble(new int[]{6, 3, 5, 2, 1, 7});
        Long sortNum = bubbleSort.getSortNum();
    }
}
