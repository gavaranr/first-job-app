package com.naveenx.firstjobapp.reviews;

import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {

    Review getCompanyReviewById(Long companyId, Long id);

    void createReview(Long companyId, Review review);

    List<Review> getAllReviewsByCompanyId(Long companyId);

    boolean editReviewByCompanyIdReviewId(Long companyId, Long reviewId, Review review);

    boolean deleteReviewByCompanyIdReviewId(Long companyId, Long reviewId);
}
