package com.xkupc.crawler.service.impl;

import com.xkupc.crawler.dto.TcVideoDTO;
import com.xkupc.crawler.mapper.TcVideoMapper;
import com.xkupc.crawler.model.TcVideo;
import com.xkupc.crawler.service.TcVideoService;
import com.xkupc.crawler.util.SnowFlakeKeyGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 5:24
 * @description
 */
@Service
public class TcVideoServiceImpl implements TcVideoService {
    @Autowired
    TcVideoMapper tcVideoMapper;
    @Autowired
    SnowFlakeKeyGen snowFlakeKeyGen;

    @Override
    public void addVideo(TcVideo tcVideo) {
        tcVideoMapper.insertSelective(tcVideo);
    }

    @Override
    public void addVideoList(List<TcVideo> tcVideoList) {
        for (TcVideo tcVideo : tcVideoList) {
            tcVideo.setVideoId(snowFlakeKeyGen.nextStringId());
            tcVideo.setCreateBy("crawler");
            tcVideo.setCreateDate(new Date());
            this.addVideo(tcVideo);
        }
    }
}
