package com.naveenx.firstjobapp.reviews;

import com.naveenx.firstjobapp.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

        // Existing methods inherited from JpaRepository
        @Query("SELECT r FROM Review r WHERE r.company.id = :companyId AND r.id = :reviewId")
        Review findReviewByCompanyIdAndReviewId(Long companyId, Long reviewId);

}
