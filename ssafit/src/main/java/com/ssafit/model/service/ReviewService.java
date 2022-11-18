package com.ssafit.model.service;

import com.ssafit.model.dto.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getReviewList(String videoId);

    Review getReview(int reviewId);

    void createReview(Review review);

    boolean modifyReview(Review review);

    boolean removeReview(int reviewId);

}
