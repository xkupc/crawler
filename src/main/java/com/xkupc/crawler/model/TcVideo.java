package com.xkupc.crawler.model;


/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 5:05
 * @description
 */
public class TcVideo extends BaseModel {

    private static final long serialVersionUID = -486866975646786751L;

    private String videoId;

    private String videoUrl;

    private String videoName;

    private String videoMark;

    private String videoType;

    private String videoImgUrl;

    private String videoScore;

    private String videoDesc;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId == null ? null : videoId.trim();
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName == null ? null : videoName.trim();
    }

    public String getVideoMark() {
        return videoMark;
    }

    public void setVideoMark(String videoMark) {
        this.videoMark = videoMark == null ? null : videoMark.trim();
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType == null ? null : videoType.trim();
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl == null ? null : videoImgUrl.trim();
    }

    public String getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(String videoScore) {
        this.videoScore = videoScore == null ? null : videoScore.trim();
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc == null ? null : videoDesc.trim();
    }
}