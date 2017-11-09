package com.xkupc.crawler.dto;

import java.io.Serializable;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 5:09
 * @description
 */
public class BaseDTO implements Serializable {
    /**
     * 分页数
     */
    private int pageNumber;

    /**
     * 分页大小
     */
    private int pageSize;

    public int getPageNumber() {
        if (pageNumber > 0) {
            return (pageNumber - 1) * this.getPageSize();
        }
        return 0;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取总页数
     *
     * @param numTotal 总条数
     * @param pageSize 每页数目
     * @return
     */
    public int getTotalPages(long numTotal, int pageSize) {
        if (pageSize <= 0) {
            return 0;
        }
        int totalPages = 0;
        if (numTotal % pageSize == 0) {
            totalPages = (int) (numTotal / pageSize);
        } else {
            totalPages = (int) (numTotal / pageSize + 1);
        }
        return totalPages;
    }
}
