package com.xkupc.crawler.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author xk
 * @createTime 2017/11/6 0006 下午 5:13
 * @description
 */
public class TcVideoDTO extends BaseDTO {

    private String videoId;

    private String videoUrl;

    private String videoName;

    private String videoMark;

    private String videoType;

    private String videoImgUrl;

    private String videoScore;

    private String videoDesc;

    private String status;

    private String remark;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    private String enableFlag;

    //创建人
    private String createBy;

    //更新人
    private String lastUpdateBy;

    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastUpdateDate;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoMark() {
        return videoMark;
    }

    public void setVideoMark(String videoMark) {
        this.videoMark = videoMark;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoImgUrl() {
        return videoImgUrl;
    }

    public void setVideoImgUrl(String videoImgUrl) {
        this.videoImgUrl = videoImgUrl;
    }

    public String getVideoScore() {
        return videoScore;
    }

    public void setVideoScore(String videoScore) {
        this.videoScore = videoScore;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "TcVideoDTO{" +
                "videoId='" + videoId + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoName='" + videoName + '\'' +
                ", videoMark='" + videoMark + '\'' +
                ", videoType='" + videoType + '\'' +
                ", videoImgUrl='" + videoImgUrl + '\'' +
                ", videoScore='" + videoScore + '\'' +
                ", videoDesc='" + videoDesc + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", createDate=" + createDate +
                ", enableFlag='" + enableFlag + '\'' +
                ", createBy='" + createBy + '\'' +
                ", lastUpdateBy='" + lastUpdateBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}
