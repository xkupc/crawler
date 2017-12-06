package com.xkupc.crawler.util;

/**
 * @author xk
 * @createTime 2017/11/20 0020 上午 10:42
 * @description 选择排序
 */
public class ChooseSort {

    private Long sortNum =Long.valueOf(0);
    /**
     * 选择排序，每次循环选择一个最值
     *
     * @param num
     */
    public int[] choose(int[] num) {
        int temp;
        for (int i = 0; i < num.length - 1; i++) {
            int index = i;
            for (int j = i + 1; j < num.length; j++) {
                if (num[index] > num[j]) {
                    index = j;
                    sortNum++;
                }
            }
            if (index != i){
                temp = num[i];
                num[i] = num[index];
                num[index] = temp;
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

    public static void main(String[] args){
      ChooseSort chooseSort = new ChooseSort();
      int[] num =chooseSort.choose(new int[]{6,3,5,2,1,7});
      Long sortNum = chooseSort.getSortNum();
    }
}
