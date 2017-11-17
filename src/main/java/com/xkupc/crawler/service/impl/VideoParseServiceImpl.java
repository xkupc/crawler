package com.xkupc.crawler.service.impl;


import com.xkupc.crawler.model.TcVideo;
import com.xkupc.crawler.service.ParseService;
import org.assertj.core.util.Lists;
import org.assertj.core.util.Strings;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 6:28
 * @description 解析视频
 */
public class VideoParseServiceImpl implements ParseService<TcVideo> {

    @Override
    public List<TcVideo> praseHtml(String result) {
        if (Strings.isNullOrEmpty(result)) {
            return null;
        }
        List<TcVideo> videoList = Lists.newArrayList();
        Document document = Jsoup.parse(result);
        if (null == document) {
            return null;
        }
        Elements elements = document.select("ul[class=figure_list]").select("li[class=list_item]");
        for (Element element : elements) {
            Element mainElement = null;
            Element assistElement = null;
            if (element.childNodeSize() == 5) {
                mainElement = element.child(0);
                assistElement = element.child(1);
            }
            if (null == mainElement) {
                continue;
            }
            boolean isVip = false;
            if (mainElement.childNodeSize() == 7) {
                //获取影片的标记
                String mark = getVideoMark(mainElement);
                //校验是否是VIP专属影片
                isVip = checkIsVipVideo(mark);
            }
            if (!isVip) {
                continue;
            }
            TcVideo tcVideo = new TcVideo();
            //获取图片地址信息
            tcVideo.setVideoUrl(getVideoUrl(mainElement));
            tcVideo.setVideoImgUrl("https:" + getVideoImageUrl(mainElement));
            tcVideo.setVideoName(getVideoName(mainElement));
            tcVideo.setVideoMark("2");
            if (Strings.isNullOrEmpty(tcVideo.getVideoUrl()) ||
                    Strings.isNullOrEmpty(tcVideo.getVideoName())) {
                continue;
            }
            tcVideo.setVideoDesc(getVideoDesc(assistElement));
            videoList.add(tcVideo);
        }
        return videoList;
    }

    /**
     * 校验是否是vip影片
     *
     * @param mark
     * @return
     */
    private boolean checkIsVipVideo(String mark) {
        if ("VIP".equals(mark)) {
            return true;
        }
        return false;
    }

    /**
     * 获取影片标记
     *
     * @param element
     * @return
     */
    private String getVideoMark(Element element) {
        if (null == element) {
            return "";
        }
        Element childrenNode = element.child(1);
        if (null == childrenNode) {
            return "";
        }
        return childrenNode.childNode(0).attr("alt");
    }

    private String getVideoUrl(Element element) {
        if (null == element) {
            return "";
        }
        return element.attr("href");
    }

    private String getVideoImageUrl(Element element) {
        if (null == element) {
            return "";
        }
        Element childrenElement = element.child(0);
        if (null == childrenElement) {
            return "";
        }
        String imageUrl = childrenElement.attr("lz_src");
        if (Strings.isNullOrEmpty(imageUrl)) {
            imageUrl = childrenElement.attr("src");
        }
        return imageUrl;
    }

    private String getVideoName(Element element) {
        if (null == element) {
            return "";
        }
        Element childrenElement = element.child(0);
        if (null == childrenElement) {
            return "";
        }
        return childrenElement.attr("alt");
    }

    private String getVideoDesc(Element element) {
        if (null == element) {
            return "";
        }
        Element childrenElement = element.child(1);
        if (null == childrenElement) {
            return "";
        }
        return childrenElement.attr("title");
    }

    private String getVideoScore(Element element) {
        if (null == element) {
            return "";
        }
        Element childrenElement = element.child(1);
        if (null == childrenElement) {
            return "";
        }
        return childrenElement.attr("title");
    }
}
