package com.xkupc.crawler.util;

/**
 * @author xk
 * @createTime 2017/12/12 0012 上午 10:22
 * @description
 */
public class TestUtil {
    private static final int MIN_MERGE = 32;

    public static void main(String[] args){
        System.err.println(50 & 4);
         System.err.println(minLength(50));
    }

    private static int minLength(int n){
        int r = 0;      // Becomes 1 if any 1 bits are shifted off
        while (n >= MIN_MERGE) {
            r |= (n & 1);
            n >>= 1;
        }
        return n + r;
    }
}
