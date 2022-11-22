package com.ssafit.model.dto;

import java.sql.Time;

public class Calendar {
    private int calendarId;
    private String userId;
    private String date;
    private String part;
    private Time starTime;
    private Time endTime;
    private String videoUrl;
    private String memo;

    public Calendar(int calendarId) {
        this.calendarId = calendarId;
    }

    public Calendar(int calendarId, String userId, String date, String part, Time starTime, Time endTime, String videoUrl, String memo) {
        this.calendarId = calendarId;
        this.userId = userId;
        this.date = date;
        this.part = part;
        this.starTime = starTime;
        this.endTime = endTime;
        this.videoUrl = videoUrl;
        this.memo = memo;
    }

    public int getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(int calendarId) {
        this.calendarId = calendarId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Time getStarTime() {
        return starTime;
    }

    public void setStarTime(Time starTime) {
        this.starTime = starTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "calendarId=" + calendarId +
                ", userId='" + userId + '\'' +
                ", date='" + date + '\'' +
                ", part='" + part + '\'' +
                ", starTime=" + starTime +
                ", endTime=" + endTime +
                ", videoUrl='" + videoUrl + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
