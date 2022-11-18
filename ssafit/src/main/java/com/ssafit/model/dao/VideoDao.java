package com.ssafit.model.dao;

import com.ssafit.model.dto.Video;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


public interface VideoDao {
    // 영상 목록 조회
//    List<Video> selectVideoList(HashMap<String, String> params);

    // 영상 조회
    Video selectVideo(String id);

    // 영상 등록
    int insertVideo(Video video);

    // 영상 수정.. 일단 조회수 증가용
    int updateVideo(Video video);
    // 영상 삭제
    int deleteVideo(String id);





}
