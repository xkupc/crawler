package com.xkupc.crawler.service;

import com.xkupc.crawler.dto.PageDTO;
import com.xkupc.crawler.dto.TcVideoDTO;
import com.xkupc.crawler.model.TcVideo;

import java.util.List;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 5:23
 * @description
 */
public interface TcVideoService {
    /**
     * 新增视频记录
     *
     * @param tcVideo
     */
    void addVideo(TcVideo tcVideo);

    void addVideoList(List<TcVideo> tcVideoList);

    /**
     * 查询视频列表
     *
     * @param tcVideoDTO
     * @return
     */
   PageDTO<TcVideo> queryVideoList(TcVideoDTO tcVideoDTO);
}
