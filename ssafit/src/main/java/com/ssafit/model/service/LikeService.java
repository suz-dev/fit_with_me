package com.ssafit.model.service;

import com.ssafit.model.dto.Video;

import java.util.HashMap;
import java.util.List;

public interface LikeService {

    void createLike(HashMap<String, String> params);

    List<Video> getLike(String userId);

    boolean removeLike(HashMap<String, String> params);
}
