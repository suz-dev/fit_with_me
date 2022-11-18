package com.ssafit.model.dao;

import com.ssafit.model.dto.Video;

import java.util.HashMap;
import java.util.List;

public interface LikeDao {

    void insertLike(HashMap<String, String> params);

    List<Video> selectAllLike(String userId);

    int deleteLike(HashMap<String, String> params);


}
