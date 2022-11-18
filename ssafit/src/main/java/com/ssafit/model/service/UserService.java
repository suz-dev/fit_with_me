package com.ssafit.model.service;

import com.ssafit.model.dto.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    void createUser(User user);

    boolean modifyUser(User user);

    // userId로 삭제
    boolean removeUser(String userId);

    // 로그인 회원 조회
    User getLoginUSer(String userId);

    User getUser(String userId);

    List<User> getUsers();
}
