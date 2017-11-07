package com.xkupc.crawler.service;

import com.xkupc.crawler.model.BaseModel;

import java.util.List;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 6:25
 * @description 解析服务
 */
public interface ParseService<T extends BaseModel> {

    public List<T> praseHtml(String result);
}
