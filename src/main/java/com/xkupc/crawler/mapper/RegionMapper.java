package com.xkupc.crawler.mapper;


import com.xkupc.crawler.model.Region;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RegionMapper {
    int insertSelective(Region record);

    List<Region> selectList(Region record);

    int update(Region region);
}