package com.ssafit.model.service;

import com.ssafit.model.dto.User;

import java.util.HashMap;
import java.util.List;

public interface FollowService {
    
    // 회원 팔로잉
    void follow(HashMap<String, Integer> params);
    
    // 팔로우 취소
    boolean unFollow(HashMap<String, Integer> params);
    
    // 팔로잉 리스트
    public List<User> getFollowingList(int fromUser);

    // 팔로워 리스트
    public List<User> getFollowerList(int toUser);

    public List<HashMap<String, String>> getFollowWishList(int fromUser);

}