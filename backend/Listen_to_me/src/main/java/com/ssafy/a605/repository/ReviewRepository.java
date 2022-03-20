package com.ssafy.a605.repository;

import com.ssafy.a605.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findById(int reviewId);
    Page<Review> findReviewsByCounselor_Email(String userEmail, Pageable pageRequest);
}
