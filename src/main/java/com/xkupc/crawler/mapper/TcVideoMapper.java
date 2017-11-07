package com.xkupc.crawler.mapper;


import com.xkupc.crawler.dto.TcVideoDTO;
import com.xkupc.crawler.model.TcVideo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TcVideoMapper {

    int insertSelective(TcVideo record);

    List<TcVideo> selectList(TcVideoDTO record);

    int insertList(List<TcVideo> tcVideoList);

}