package org.petspa.petcaresystem.review.service;

import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.review.model.request.UpdateReviewRequestDTO;
import org.petspa.petcaresystem.review.model.response.ResponseInfor;
import org.petspa.petcaresystem.review.model.response.ReviewResponseDTO;
import org.petspa.petcaresystem.review.model.response.ReviewResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ReviewResponseDTO findAllReview();
    ReviewResponseDTO findReviewByAuthor(Long userId);
    ResponseInfor updateStatusReview(Long reviewId, UpdateReviewRequestDTO updateReviewRequestDTO);
}

