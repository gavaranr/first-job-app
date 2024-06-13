package com.naveenx.firstjobapp.reviews;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews (@PathVariable Long companyId) {

        return new ResponseEntity<>(reviewService.getAllReviewsByCompanyId(companyId), HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewByIdByCompanyId (@PathVariable Long companyId, @PathVariable Long reviewId) {

        return new ResponseEntity<>(reviewService.getCompanyReviewById(companyId, reviewId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReview (@PathVariable Long companyId, @RequestBody Review review) {

        boolean isCreated = reviewService.createReview(companyId, review);

        return isCreated? new ResponseEntity<>(
                "Review created successfully", HttpStatus.OK) :
                new ResponseEntity<>("Company with ID " + companyId + " doesn't exist. You can add a review only to a company that exists", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> editReview (@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        boolean updated = reviewService.editReviewByCompanyIdReviewId(companyId, reviewId, review);
        return updated? new ResponseEntity<>("Successfully updated", HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview (@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean deleted = reviewService.deleteReviewByCompanyIdReviewId(companyId, reviewId);
        return deleted?
                new ResponseEntity<>("Deleted the review successfully", HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}