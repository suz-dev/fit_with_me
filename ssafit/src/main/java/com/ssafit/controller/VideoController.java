package com.ssafit.controller;

import com.ssafit.model.dto.Review;
import com.ssafit.model.dto.Video;

import com.ssafit.model.service.ReviewService;
import com.ssafit.model.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/videoapi")
public class VideoController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    VideoService videoService;

    @Autowired
    ReviewService reviewService;


//    @GetMapping("/video")
//    public ResponseEntity<?> getVideoList(@RequestParam(defaultValue = "") String mode, @RequestParam(defaultValue = "") String keyword) {
//        HashMap<String, String> params = new HashMap<String, String>();
//
//        params.put("mode", mode);
//        params.put("keyword", keyword);
//        List<Video> videoList = videoService.getVideoList(params);
//        if (videoList != null && videoList.size() > 0)
//            return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
//        else
//            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/video/{id}")
    public ResponseEntity<?> getVideoDetail(@PathVariable String id) {
        Video video = videoService.getVideoById(id);
        System.out.println(video);
        System.out.println(video==null);

        if (video != null) {
            return new ResponseEntity<Video>(video, HttpStatus.OK);
        } else return new ResponseEntity<String>(FAIL, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/video")
    public ResponseEntity<Integer> createVideo(Video video) {
        videoService.createVideo(video);
        return new ResponseEntity<Integer>(1, HttpStatus.OK);
    }

//    @DeleteMapping("/video/{id}")
//    public ResponseEntity<?> removeVideo(@PathVariable String id) {
//        boolean result = videoService.removeVideo(id);
//        if (result)
//            return new ResponseEntity<Boolean>(result, HttpStatus.OK);
//        else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/review")
    public ResponseEntity<List<Review>> getReviewListByVideoId(@RequestParam String videoId) {
        List<Review> reviewList = reviewService.getReviewList(videoId);
        return new ResponseEntity<>(reviewList, HttpStatus.OK);
    }

    @GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable int reviewId) {
        Review review = reviewService.getReview(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReview(Review review) {
        reviewService.createReview(review);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/review")
    public ResponseEntity<?> modifyReview(Review review) {
        System.out.println(review.getReviewId());
        boolean result = reviewService.modifyReview(review);
        if (result)
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/review/{id}")
    public ResponseEntity<?> removeReview(@PathVariable int id) {
        boolean result = reviewService.removeReview(id);
        if (result)
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

}
