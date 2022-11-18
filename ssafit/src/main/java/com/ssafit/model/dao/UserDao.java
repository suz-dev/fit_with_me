package com.ssafit.model.dao;

import com.ssafit.model.dto.User;

import java.util.HashMap;
import java.util.List;

public interface UserDao {

    // 회원 가입
    int insertUser(User user);

    // 회원 정보 수정
    int updateUser(User user);

    // 회원 삭제
    int deleteUser(int userSeq);

    // 회원 조회 (userId 기준)
    User selectUser(String userId);

    // 회원 목록 조회
    List<User> selectUserList(HashMap<String, String> params);
}
