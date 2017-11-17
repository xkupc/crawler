package com.xkupc.crawler.service.impl;

import com.xkupc.crawler.mapper.RegionMapper;
import com.xkupc.crawler.model.Region;
import com.xkupc.crawler.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xk
 * @createTime 2017/11/16 0016 下午 5:15
 * @description
 */
@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addRegion(Region region) {
        regionMapper.insertSelective(region);
    }

    @Override
    public void addRegionList() {
        Map<Object, Object> provinceMap = redisTemplate.opsForHash().entries("province");
        if (null == provinceMap) {
            return;
        }
        Map<String, Region> regionList = new HashMap<>(48000);
        for (Map.Entry provinceEntry : provinceMap.entrySet()) {
            Region region = getRegion(provinceEntry.getKey().toString(), provinceEntry.getValue().toString(), "0", 1);
            regionList.put(provinceEntry.getKey().toString(), region);
            Map<Object, Object> cityMap = redisTemplate.opsForHash().entries(provinceEntry.getKey().toString());
            if (null == cityMap) {
                continue;
            }
            for (Map.Entry cityEntry : cityMap.entrySet()) {
                Region cityRegion = getRegion(cityEntry.getKey().toString(), cityEntry.getValue().toString(), provinceEntry.getKey().toString(), 2);
                regionList.put(cityEntry.getKey().toString(), cityRegion);
                Map<Object, Object> areaMap = redisTemplate.opsForHash().entries(cityEntry.getKey().toString());
                if (null == areaMap) {
                    continue;
                }
                for (Map.Entry areaEntry : areaMap.entrySet()) {
                    Region areaRegion = getRegion(areaEntry.getKey().toString(), areaEntry.getValue().toString(), cityEntry.getKey().toString(), 3);
                    regionList.put(areaEntry.getKey().toString(), areaRegion);
                    Map<Object, Object> townMap = redisTemplate.opsForHash().entries(areaEntry.getKey().toString());
                    if (null == townMap) {
                        continue;
                    }
                    for (Map.Entry townEntry : townMap.entrySet()) {
                        Region townRegion = getRegion(townEntry.getKey().toString(), townEntry.getValue().toString(), areaEntry.getKey().toString(), 4);
                        regionList.put(townEntry.getKey().toString(), townRegion);
                    }
                }
            }
        }
        if (!regionList.isEmpty()) {
            for (Map.Entry entry : regionList.entrySet()) {
                addRegion((Region) entry.getValue());
            }
        }
    }

    @Override
    public void addRegionList(Map<String, String> province) {
        if (null == province||province.isEmpty()){
            return;
        }
        Map<String, Region> regionList = new HashMap<>(1000);
        for (Map.Entry entry:province.entrySet()){
            String key = String.valueOf(Long.valueOf(entry.getKey().toString())+100);
            Region region = getRegion(key,"市辖区",entry.getKey().toString(),2);
            regionList.put(key,region);
            Map<Object, Object> areaMap = redisTemplate.opsForHash().entries(entry.getKey().toString());
            if (null == areaMap || areaMap.isEmpty()){
                continue;
            }
            for (Map.Entry areaEntry : areaMap.entrySet()) {
                Region areaRegion = getRegion(areaEntry.getKey().toString(), areaEntry.getValue().toString(), entry.getKey().toString(), 3);
                regionList.put(areaEntry.getKey().toString(), areaRegion);
                Map<Object, Object> townMap = redisTemplate.opsForHash().entries(areaEntry.getKey().toString());
                if (null == townMap || townMap.isEmpty()) {
                    continue;
                }
                for (Map.Entry townEntry : townMap.entrySet()) {
                    Region townRegion = getRegion(townEntry.getKey().toString(), townEntry.getValue().toString(), areaEntry.getKey().toString(), 4);
                    regionList.put(townEntry.getKey().toString(), townRegion);
                }
            }
        }

        if (null !=regionList){
            for (Map.Entry entry :regionList.entrySet()){
                addRegion((Region) entry.getValue());
            }
        }
    }

    @Override
    public void updateRegionPath() {
        Region paramRegion = new Region();
        paramRegion.setType(1);
        List<Region> regionList = queryRegionList(paramRegion);
        for (Region region : regionList) {
            region.setPath(region.getId());
            regionMapper.update(region);
            updateChildRegionPath(region.getId(), region.getId());
        }
    }

    @Override
    public void updateRegionPath(Map<String, String> province) {
        if (null != province&&!province.isEmpty()){
            for (Map.Entry entry : province.entrySet()) {
                Region paramRegion = new Region();
                paramRegion.setId(entry.getKey().toString());
                List<Region> regionList = queryRegionList(paramRegion);
                Region region = regionList.get(0);
                region.setPath(region.getId());
                regionMapper.update(region);
                updateChildRegionPath(region.getId(), region.getId());
            }
        }
    }

    private void updateChildRegionPath(String parentId, String path) {
        Region paramRegion = new Region();
        paramRegion.setParentId(parentId);
        List<Region> regionList = queryRegionList(paramRegion);
        if (null != regionList && !regionList.isEmpty()) {
            for (Region region : regionList) {
                region.setPath(path + "-" + region.getId());
                regionMapper.update(region);
                updateChildRegionPath(region.getId(), path + "-" + region.getId());
            }
        }
    }

    @Override
    public List<Region> queryRegionList(Region region) {
        return regionMapper.selectList(region);
    }

    private Region getRegion(String key, String value, String parentId, int type) {
        Region region = new Region();
        region.setId(key);
        region.setParentId(parentId);
        region.setAreaName(value);
        region.setType(type);
        region.setStatus("1");
        region.setCreateDate(new Date());
        return region;
    }
}
