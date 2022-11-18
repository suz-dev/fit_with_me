package com.ssafit.model.service;

import com.ssafit.model.dao.UserDao;
import com.ssafit.model.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    // 회원 가입
    @Override
    public void createUser(User user) {
        userDao.insertUser(user);
    }

    // 회원 정보 수정
    @Override
    public boolean modifyUser(User user) {
        User originUser = userDao.selectUser(user.getUserId());
        System.out.println("originUser = " + originUser.getUserId());
        System.out.println("user = " + user.getUserId());
        originUser.setPassword(user.getPassword()); // 비번 수정 -> 검증 기능 추가?
        originUser.setUserName(user.getUserName()); // 이름 수정
        return userDao.updateUser(originUser) == 1; // 업데이트
    }

    // userId로 삭제
    @Override
    public boolean removeUser(String userId) {
        return userDao.deleteUser(userId) == 1;
    }

    @Override
    public User getLoginUSer(String userId) {
        return userDao.selectLoginUser(userId);
    }

    // userId, userName 두가지로 검색
    @Override
    public User getUser(String userId) {
        return userDao.selectUser(userId);
    }

    // 전체 조회
    @Override
    public List<User> getUsers() {
        return userDao.selectAllUser();
    }
}
