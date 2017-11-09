package com.xkupc.crawler.dto;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 5:09
 * @description
 */
public class PageDTO<T> implements Serializable {
    private static final long serialVersionUID = 6268718986881793567L;

    private long totalElements;

    private List<T> rows = new ArrayList<>();

    public PageDTO() {

    }

    public PageDTO(List<T> content, long totalElements, int totalPages, int number) {
        Assert.notNull(content, "Content must not be null!");
        this.rows.addAll(content);
        this.totalElements = totalElements;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
