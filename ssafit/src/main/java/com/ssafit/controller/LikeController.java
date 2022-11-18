package com.ssafit.controller;

import com.ssafit.model.dto.Video;
import com.ssafit.model.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/wishapi")
public class LikeController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";


    @Autowired
    private LikeService likeService;


    @PostMapping("/wish")
    public ResponseEntity<?> createWish(String userId, String videoId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("videoId", videoId);
        likeService.createLike(params);
        // test
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

    }

    @GetMapping("/wish/{userId}")
    public ResponseEntity<?> getWishList(@PathVariable String userId) {
        List<Video> wishList = likeService.getLike(userId);
        return new ResponseEntity<List<Video>>(wishList, HttpStatus.OK);
    }

    @DeleteMapping("/wish")
    public ResponseEntity<?> removeWish(@RequestParam String userId, @RequestParam String videoId) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("videoId", videoId);
        System.out.println(params);
        if (likeService.removeLike(params))
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

    }

}
