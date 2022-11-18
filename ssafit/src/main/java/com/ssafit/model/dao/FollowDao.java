package com.ssafit.model.dao;

import com.ssafit.model.dto.User;
import com.ssafit.model.dto.Video;

import java.util.HashMap;
import java.util.List;

public interface FollowDao {

    // 회원 팔로잉 (등록만)
    public void addFollow(HashMap<String, String> params);

    // 팔로잉 취소
    public int unFollow(HashMap<String, String> params);

    // 팔로잉 리스트
    List<User> selectAllFollowing(String fromUser);

    // 팔로워 리스트
    List<User> selectAllFollower(String toUser);

}
