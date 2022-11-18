package com.ssafit.model.dto;

public class Video {
    private String id;
    private String title;
    private String part;
    private String channelName;

    private int viewCnt;

    public Video() {
    }

    public Video(String id, String title, String part, String channelName, int viewCnt) {
        this.id = id;
        this.title = title;
        this.part = part;
        this.channelName = channelName;

        this.viewCnt = viewCnt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }


    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }
}
