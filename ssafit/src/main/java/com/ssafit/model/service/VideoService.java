package com.ssafit.model.service;

import com.ssafit.model.dto.Video;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

public interface VideoService {

//    public List<Video> getVideoList(HashMap<String, String> params);

    Video getVideoById(String id);

    void createVideo(Video video);

//    boolean removeVideo(String id);

//    boolean updateVideo(Video video);

    boolean updateViewCnt(String id);


}
