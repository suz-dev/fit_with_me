package com.ssafit.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Review {
    private int reviewId;
    private String content;
    private String userId;
    private String userName;
    private String videoId;
    private String regDate;

    private int star;

    public Review() {
    }

    public Review(int reviewId, String content, String userId, String userName, String videoId, String regDate, int star) {
        this.reviewId = reviewId;
        this.content = content;
        this.userId = userId;
        this.userName = userName;
        this.videoId = videoId;
        this.regDate = regDate;
        this.star = star;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
