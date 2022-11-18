package com.ssafit.model.dao;

import com.ssafit.model.dto.Review;
import com.ssafit.model.dto.Video;

import java.util.List;

public interface ReviewDao {

    // 리뷰 목록 조회
    List<Review> selectAllReview(String videoId);

    // 리뷰 조회
    Review selectReview(int reviewId);

    // 리뷰 등록
    void insertReview(Review review);

    // 리뷰 수정
    int updateReview(Review review);
    // 영상 삭제
    int deleteReview(int reviewId);
}
