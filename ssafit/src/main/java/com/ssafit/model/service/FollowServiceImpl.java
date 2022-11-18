package com.ssafit.model.service;

import com.ssafit.model.dao.FollowDao;
import com.ssafit.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowDao followDao;


    @Override
    public void follow(HashMap<String, Integer> params) {
        followDao.addFollow(params);
    }

    @Override
    public boolean unFollow(HashMap<String, Integer> params) {
        return followDao.unFollow(params) == 1;
    }

    @Override
    public List<User> getFollowingList(int fromUser) {
        return followDao.selectFollowingList(fromUser);
    }

    @Override
    public List<User> getFollowerList(int toUser) {
        return followDao.selectFollowerList(toUser);
    }

    @Override
    public List<HashMap<String, String>> getFollowWishList(int fromUser) {
        return followDao.selectFollowWishList(fromUser);
    }
}
