package com.ssafit.model.service;

import com.ssafit.model.dto.User;

import java.util.HashMap;
import java.util.List;

public interface UserService {

    void createUser(User user);

    boolean modifyUser(User user);

    boolean removeUser(int userSeq);

    User getUser(String userId);

    List<User> getAllUsers(HashMap<String, String> params);
}
