package com.xkupc.crawler.service;

import com.xkupc.crawler.BaseTestService;
import com.xkupc.crawler.model.Region;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xk
 * @createTime 2017/11/16 0016 下午 6:39
 * @description
 */
public class AddressService extends BaseTestService {

    @Autowired
    RegionService regionService;

    @Test
    public void addRegionTest(){
        regionService.addRegion(getRegion());
    }
    @Test
    public void addRegionList(){
        regionService.addRegionList();
    }
    @Test
    public void addRegionListTest(){
        regionService.addRegionList(province());
    }
    @Test
    public void updateRegion(){
        regionService.updateRegionPath();
    }
    @Test
    public void updateRegionTest(){
        regionService.updateRegionPath(province());
    }
    private Region getRegion(){
        Region region = new Region();
        region.setId("123000");
        region.setParentId("0");
        region.setAreaName("test");
        region.setType(1);
        region.setCreateDate(new Date());
        return region;
    }

    private Map<String, String> province() {
        Map<String, String> map = new HashMap<>();
        map.put("110000", "北京市");
        map.put("120000", "天津市");
        map.put("310000", "上海市");
        map.put("500000", "重庆市");
        map.put("810000", "香港特别行政区");
        map.put("820000", "澳门特别行政区");
        return map;
    }
}
