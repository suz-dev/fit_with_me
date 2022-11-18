package com.ssafit.model.service;

import com.ssafit.model.dto.User;

import java.util.HashMap;
import java.util.List;

public interface FollowService {
    
    // 회원 팔로잉
    void addFollow(HashMap<String, String> params);

    // 팔로우 취소
    boolean unFollow(HashMap<String, String> params);
    
    // 팔로잉 리스트
    public List<User> getFollowing(String fromUser);

    // 팔로워 리스트
    public List<User> getFollower(String toUser);

}