package com.naveenx.firstjobapp.reviews.Impl;

import com.naveenx.firstjobapp.reviews.Review;
import com.naveenx.firstjobapp.reviews.ReviewService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {
    private Long nextReviewId = 1L;
    private final Map<Long, Map<Long, Review>> companyReviewMap = new HashMap<>();

    @Override
    public Review getCompanyReviewById(Long companyId, Long reviewId) {

        Map<Long, Review> reviewMap = companyReviewMap.get(companyId);

        return reviewMap.get(reviewId);
    }

    @Override
    public void createReview(Long companyId, Review review) {

        companyReviewMap.computeIfAbsent(companyId, key -> new HashMap<>()).put(nextReviewId++, review);

    }

    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {

        if(!companyReviewMap.containsKey(companyId)) {
            return new ArrayList<>();
        }

        Map<Long, Review> reviewMap = companyReviewMap.get(companyId);

        return new ArrayList<>(reviewMap.values());
    }

    @Override
    public boolean editReviewByCompanyIdReviewId(Long companyId, Long reviewId, Review review) {
        // Check if the company exists in the map
        if (!companyReviewMap.containsKey(companyId)) {
            return false;
        }

        // Get the inner map of reviews for the company
        Map<Long, Review> reviewMap = companyReviewMap.get(companyId);

        // Check if the review exists in the company's review map
        if (!reviewMap.containsKey(reviewId)) {
            return false;
        }

        // Update the review with the provided review object
        reviewMap.put(reviewId, review);

        return true;
    }

    @Override
    public boolean deleteReviewByCompanyIdReviewId(Long companyId, Long reviewId) {

        if(!companyReviewMap.containsKey(companyId)) {
            return false;
        }

        Map<Long, Review> reviewMap = companyReviewMap.get(companyId);

        if(!reviewMap.containsKey(reviewId)) {
            return false;
        }

        reviewMap.remove(reviewId);

        if(reviewMap.isEmpty()) {
            companyReviewMap.remove(companyId);
        }
        return true;
    }
}
