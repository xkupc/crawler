package com.xkupc.crawler.service.impl;

import com.xkupc.crawler.dto.PageDTO;
import com.xkupc.crawler.dto.TcVideoDTO;
import com.xkupc.crawler.mapper.TcVideoMapper;
import com.xkupc.crawler.model.TcVideo;
import com.xkupc.crawler.service.TcVideoService;
import com.xkupc.crawler.util.SnowFlakeKeyGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

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
    ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addVideo(TcVideo tcVideo) {
        tcVideoMapper.insertSelective(tcVideo);
        executorService.execute(() -> {
            try {
                System.err.println("我开始睡了");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("我醒了,你呢");
            throw new RuntimeException();
        });
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

    @Override
    public PageDTO<TcVideo> queryVideoList(TcVideoDTO tcVideoDTO) {
        PageDTO<TcVideo> pageDTO = new PageDTO<>();
        //查询记录总数
        Long count = tcVideoMapper.selectListCount(tcVideoDTO);
        if (null != count && count > 0) {
            List<TcVideo> tcVideoList = tcVideoMapper.selectList(tcVideoDTO);
            pageDTO.setRows(tcVideoList);
        }
        pageDTO.setTotalElements(count);
        return pageDTO;
    }
}
