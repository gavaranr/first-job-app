package com.naveenx.firstjobapp.reviews.Impl;

import com.naveenx.firstjobapp.company.Company;
import com.naveenx.firstjobapp.company.CompanyRepository;
import com.naveenx.firstjobapp.company.CompanyService;
import com.naveenx.firstjobapp.reviews.Review;
import com.naveenx.firstjobapp.reviews.ReviewRepository;
import com.naveenx.firstjobapp.reviews.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public ReviewServiceImpl() {
        // Optional: Initialize fields here if needed
    }
    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyRepository companyRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    @Override
    public Review getCompanyReviewById(Long companyId, Long reviewId) {

        return reviewRepository.findReviewByCompanyIdAndReviewId(companyId, reviewId);

    }

    @Override
    public boolean createReview(Long companyId, Review review) {

        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {

        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        if (!reviews.isEmpty()) {
            return reviews;
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean editReviewByCompanyIdReviewId(Long companyId, Long reviewId, Review updatedReview) {

        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        } else return false;
    }


    @Override
    public boolean deleteReviewByCompanyIdReviewId(Long companyId, Long reviewId) {

        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {

            Review review = reviewRepository.findById(reviewId).orElse(null);
            assert review != null;
            Company company = review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        } else {
            return false;
        }
    }
}
