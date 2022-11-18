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
    public void addFollow(HashMap<String, String> params) {
        followDao.addFollow(params);
    }

    @Override
    public boolean unFollow(HashMap<String, String> params){
        return followDao.unFollow(params) == 1;
    }

    @Override
    public List<User> getFollowing(String fromUser) {
        return followDao.selectAllFollowing(fromUser);
    }

    @Override
    public List<User> getFollower(String toUser) {
        return followDao.selectAllFollower(toUser);
    }


}
