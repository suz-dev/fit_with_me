package com.ssafit.model.service;

import com.ssafit.model.dao.LikeDao;
import com.ssafit.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeDao likeDao;


    @Override
    public void createLike(HashMap<String, String> params) {
        likeDao.insertLike(params);
    }

    @Override
    public List<Video> getLike(String userId) {
        return likeDao.selectAllLike(userId);
    }

    @Override
    public boolean removeLike(HashMap<String, String> params) {
        return likeDao.deleteLike(params)==1;
    }
}
