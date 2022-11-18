package com.ssafit.model.dao;

import com.ssafit.model.dto.User;
import com.ssafit.model.dto.Video;

import java.util.HashMap;
import java.util.List;

public interface FollowDao {

    // 회원 팔로잉 (등록만)
    public void addFollow(HashMap<String, Integer> params);

    // 팔로잉 취소
    public int unFollow(HashMap<String, Integer> params);

    // 팔로잉 리스트
    List<User> selectFollowingList(int fromUser);

    // 팔로워 리스트
    List<User> selectFollowerList(int toUser);

    // 팔로우한 사람이 찜한 영상 리스트
    List<HashMap<String, String>> selectFollowWishList(int fromUser);
}
