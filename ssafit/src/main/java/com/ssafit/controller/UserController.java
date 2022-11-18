package com.ssafit.controller;

import com.ssafit.model.dto.User;
import com.ssafit.model.service.FollowService;
import com.ssafit.model.service.UserService;
import com.ssafit.util.JwtUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userapi")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FollowService followService;
    @Autowired
    private JwtUtil jwtUtil;

    // 확인용
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<String> create(User user) {
        userService.createUser(user);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
    }

    // jwt login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> doLogin(User user) {

        HashMap<String, Object> result = new HashMap<>();
        HttpStatus status = null;

        User confirm = userService.getUser(user.getUserId());
        try {
            if (confirm != null && user.getUserId() != null || user.getUserId().length() > 0) {
                result.put("access-token", jwtUtil.createToken("id", user.getUserId()));
                result.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                result.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (UnsupportedEncodingException e) {
            result.put("message", FAIL);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<Map<String, Object>>(result, status);
    }

    // 로그인
//    @PostMapping("/login")
//    public ResponseEntity<?> doLogin(User user, HttpSession session) {
//        User confirm = userService.getUser(user.getUserId());
//        if (confirm != null && user.getUserId().equals(confirm.getUserId()) && user.getPassword().equals(confirm.getPassword())) {
//            session.setAttribute("loginUser", confirm.getUserName());
//
//            return new ResponseEntity<User>(confirm, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//        }
//    }

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<?> doLogout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }


    // 회원 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<User> detail(@PathVariable String userId) {
        // 추가 예외처리
        return new ResponseEntity<User>(userService.getUser(userId), HttpStatus.OK);
    }

    // 회원 전체 조회
    @GetMapping("/user")
    public ResponseEntity<List<User>> list(@RequestParam(defaultValue = "") String mode,
                                           @RequestParam(defaultValue = "") String keyword) {
        HashMap<String, String> params = new HashMap<>();

        params.put("mode", mode);
        params.put("keyword", keyword);

        return new ResponseEntity<List<User>>(userService.getAllUsers(params), HttpStatus.OK);
    }

    // 회원 정보 수정
    @PutMapping("/user")
    public ResponseEntity<String> update(User user) {
        userService.modifyUser(user);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 회원 삭제?
    @DeleteMapping("/user/{userSeq}")
    public ResponseEntity<String> delete(@PathVariable int userSeq) {
        if (userService.removeUser(userSeq)) {
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);
    }

    // 팔로우
    @PostMapping("/follow")
    public ResponseEntity<?> follow(int fromUser, int toUser) {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("fromUser", fromUser);
        params.put("toUser", toUser);
        followService.follow(params);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 언팔로우
    @DeleteMapping("/follow")
    public ResponseEntity<?> unfollow(@RequestParam int fromUser, @RequestParam int toUser) {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("fromUser", fromUser);
        params.put("toUser", toUser);
        if (followService.unFollow(params))
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    // 팔로잉 리스트 (from_user로 검색)
    @GetMapping("/following/{fromUser}")
    public ResponseEntity<List<User>> getFollowingList(@PathVariable int fromUser) {
        List<User> followingList = followService.getFollowingList(fromUser);
        return new ResponseEntity<List<User>>(followingList, HttpStatus.OK);
    }


    // 팔로워 리스트 (to_user로 검색)
    @GetMapping("/follower/{toUser}")
    public ResponseEntity<List<User>> getFollowerList(@PathVariable int toUser) {
        List<User> followerList = followService.getFollowerList(toUser);
        return new ResponseEntity<List<User>>(followerList, HttpStatus.OK);
    }

    @GetMapping("/following/wish/{fromUser}")
    public ResponseEntity<List<HashMap<String, String>>> getFollowWishList(@PathVariable int fromUser) {
        List<HashMap<String, String>> followWishList = followService.getFollowWishList(fromUser);
        return new ResponseEntity<>(followWishList, HttpStatus.OK);
    }
}
