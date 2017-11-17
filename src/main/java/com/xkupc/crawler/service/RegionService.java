package com.xkupc.crawler.service;

import com.xkupc.crawler.model.Region;

import java.util.List;
import java.util.Map;

/**
 * @author xk
 * @createTime 2017/11/16 0016 下午 5:11
 * @description
 */
public interface RegionService {
    /**
     * 新增地址
     *
     * @param region
     */
    public void addRegion(Region region);

    /**
     * 从缓存获取地址列表并保存
     */
    public void addRegionList();

    public void addRegionList(Map<String,String> province);

    public void updateRegionPath();

    public void updateRegionPath(Map<String,String> province);

    List<Region> queryRegionList(Region region);
}
