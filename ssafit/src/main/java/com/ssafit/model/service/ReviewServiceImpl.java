package com.ssafit.model.service;

import com.ssafit.model.dao.ReviewDao;
import com.ssafit.model.dto.Review;
import com.ssafit.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewDao reviewDao;

    @Override
    public List<Review> getReviewList(String videoId) {
        return reviewDao.selectAllReview(videoId);
    }

    @Override
    public Review getReview(int reviewId) {
        return reviewDao.selectReview(reviewId);
    }

    @Override
    public void createReview(Review review) {
        reviewDao.insertReview(review);
    }

    @Override
    public boolean modifyReview(Review review) {
        // 수정 가능한 건  내용, 별점만


        Review originReview = reviewDao.selectReview(review.getReviewId());

        originReview.setProfile(review.getProfile());
        originReview.setUserName(review.getUserName());

        originReview.setContent(review.getContent());
        originReview.setStar(review.getStar());


        return reviewDao.updateReview(originReview) == 1;
    }

    @Override
    public boolean removeReview(int reviewId) {
        return reviewDao.deleteReview(reviewId) == 1;
    }


}
