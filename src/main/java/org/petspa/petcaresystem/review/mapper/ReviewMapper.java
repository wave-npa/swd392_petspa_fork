package org.petspa.petcaresystem.review.mapper;

import org.petspa.petcaresystem.review.model.entity.Review;
import org.petspa.petcaresystem.review.model.response.ReviewResponse;

public class ReviewMapper {
    public static ReviewResponse toReviewResponse(Review review){
        return ReviewResponse.builder()
                .reviewId(review.getReviewId())
                .description(review.getDescription())
                .rating(review.getRating())
                .author(review.getAuthor())
                .appointment(review.getAppointment())
                .status(review.getStatus())
                .build();
    }
}
