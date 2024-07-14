package org.petspa.petcaresystem.review.service;

import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.request.CreatePetRequest;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    ResponseEntity<ResponseObj> ViewAllReview();
    ResponseEntity<ResponseObj> ViewReviewByAuthor(Long Author_id);
    ResponseEntity<ResponseObj> SortReviewByRating(ReviewRating rating);
//    ResponseEntity<ResponseObj> CreateReview(Long author_id, CreatePetRequest petRequest);
//    ResponseEntity<ResponseObj> UpdateReview(Long review_id, UpdatePetRequest petRequest);
    ResponseEntity<ResponseObj> DeleteReview(Long review_id);
    ResponseEntity<ResponseObj> RestoreReview(Long review_id);
    ResponseEntity<ResponseObj> RemoveReview(Long review_id);
}

