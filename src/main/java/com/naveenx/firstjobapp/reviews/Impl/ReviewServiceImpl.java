package com.naveenx.firstjobapp.reviews.Impl;

import com.naveenx.firstjobapp.company.Company;
import com.naveenx.firstjobapp.company.CompanyRepository;
import com.naveenx.firstjobapp.reviews.Review;
import com.naveenx.firstjobapp.reviews.ReviewRepository;
import com.naveenx.firstjobapp.reviews.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public ReviewServiceImpl() {
        // Optional: Initialize fields here if needed
    }
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    @Override
    public Review getCompanyReviewById(Long companyId, Long reviewId) {

        return reviewRepository.findReviewByCompanyIdAndReviewId(companyId, reviewId);

    }

    @Override
    public void createReview(Long companyId, Review review) {

        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            // Set the company for the review (assuming a relationship)
            review.setCompany(company);

            // Save the company (JPA will cascade the review)
            companyRepository.save(company);
        } else {
            // Handle case where company is not found (log error or throw exception)
            throw new RuntimeException("Company with ID: " + companyId + " not found."); // Example handling
        }
    }


    @Override
    public List<Review> getAllReviewsByCompanyId(Long companyId) {

        Optional<Company> companyOptional = companyRepository.findById(companyId);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            return company.getReviews();
        }
        else {
            return Collections.emptyList();
        }
    }

    @Override
    public boolean editReviewByCompanyIdReviewId(Long companyId, Long reviewId, Review review) {

        // 1. Find the existing review
        Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);

        // 2. Check if review exists and belongs to the company
        if (existingReviewOptional.isPresent() &&
                existingReviewOptional.get().getCompany().getId().equals(companyId)) {

            // Update existing review object with new data (considering immutability)
            Review existingReview = existingReviewOptional.get();
            // Update the existing review from the provided "review" object
            existingReview.setReview(review.getReview());

            // 3. Save the updated review
            reviewRepository.save(existingReview);

            return true; // Successful update
        } else {
            // Review not found or doesn't belong to the company
            return false; // Update failed
        }
    }


    @Override
    public boolean deleteReviewByCompanyIdReviewId(Long companyId, Long reviewId) {

        // 1. Find the existing review
        Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);

        // 2. Check if review exists and belongs to the company
        if (existingReviewOptional.isPresent() &&
                existingReviewOptional.get().getCompany().getId().equals(companyId)) {
            reviewRepository.deleteById(reviewId);
            return true; // Explicitly return true on successful deletion
        } else {
            return false; // Explicitly return false if review not found or wrong company
        }
    }
}
