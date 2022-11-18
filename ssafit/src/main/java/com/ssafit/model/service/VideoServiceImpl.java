package com.ssafit.model.service;

import com.ssafit.model.dao.VideoDao;
import com.ssafit.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoDao videoDao;

//    @Override
//    public List<Video> getVideoList(HashMap<String, String> params) {
//        return videoDao.selectVideoList(params);
//    }

    @Override
    public Video getVideoById(String id) {
//        this.updateViewCnt(id);
        return videoDao.selectVideo(id);
    }

    @Override
    public void createVideo(Video video) {
        videoDao.insertVideo(video);
    }

//    @Override
//    public boolean removeVideo(String id) {
//        return videoDao.deleteVideo(id)==1;
//    }

//    @Override
//    public boolean updateVideo(Video video) {
//        Video originVideo = videoDao.selectVideo(video.getId());
//        originVideo.setViewCnt(video.getViewCnt());
//        return videoDao.updateVideo(originVideo)==1;
//    }

    @Override
    public boolean updateViewCnt(String id) {
        Video video = videoDao.selectVideo(id);
        video.setViewCnt(video.getViewCnt()+1);
        return videoDao.updateVideo(video)==1;
    }
}
