package org.petspa.petcaresystem.review.service.implement;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.mapper.PetMapper;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.response.PetResponse;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.review.mapper.ReviewMapper;
import org.petspa.petcaresystem.review.model.entity.Review;
import org.petspa.petcaresystem.review.model.response.ReviewResponse;
import org.petspa.petcaresystem.review.repository.ReviewRepository;
import org.petspa.petcaresystem.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    private AuthenUserRepository authenUserRepository;

    @Override
    public ResponseEntity<ResponseObj> ViewAllReview() {
        try {
            List<Review> reviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
            if (reviewList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no reviews created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            List<ReviewResponse> reviewResponses = new ArrayList<>();
            for (Review review : reviewList) {
                ReviewResponse reviewResponse = ReviewMapper.toReviewResponse(review);
                reviewResponses.add(reviewResponse);
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Load reviews Successfully")
                    .data(reviewResponses)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> ViewReviewByAuthor(Long Author_id) {
        try {
            AuthenUser author = authenUserRepository.getReferenceById(Author_id);
            List<AuthenUser> authenUserList = authenUserRepository.findAll();
            for (AuthenUser authenUser : authenUserList) {
                if (authenUser.equals(author) && authenUser.getStatus().equals(Status.ACTIVE)) {
                    Collection<Review> reviewList = authenUser.getWrittenReview();
                    if (reviewList.isEmpty()) {
                        ResponseObj responseObj = ResponseObj.builder()
                                .message("There are no reviews created yet")
                                .data(null)
                                .build();
                        return ResponseEntity.ok().body(responseObj);
                    } else {
                        List<ReviewResponse> reviewResponses = new ArrayList<>();
                        for (Review review : reviewList) {
                            ReviewResponse reviewResponse = ReviewMapper.toReviewResponse(review);
                            reviewResponses.add(reviewResponse);
                        }
                        ResponseObj responseObj = ResponseObj.builder()
                                .message("Load reviews Successfully")
                                .data(reviewResponses)
                                .build();
                        return ResponseEntity.ok().body(responseObj);
                    }
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Author not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> SortReviewByRating(ReviewRating rating) {
        try {
            List<Review> reviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
            if (reviewList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no reviews created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            List<ReviewResponse> reviewResponses = new ArrayList<>();
            for (Review review : reviewList) {
                if (review.getRating().equals(rating)) {
                    ReviewResponse reviewResponse = ReviewMapper.toReviewResponse(review);
                    reviewResponses.add(reviewResponse);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Load reviews Successfully")
                    .data(reviewResponses)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> DeleteReview(Long review_id) {
        try {
            Review deletereview = reviewRepository.getReferenceById(review_id);
            List<Review> reviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
            if (reviewList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no reviews created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (Review review : reviewList) {
                if (review.equals(deletereview) && review.getStatus().equals(Status.ACTIVE)) {
                    review.setStatus(Status.INACTIVE);
                    reviewRepository.save(review);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Delete review Successfully")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Review not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> RestoreReview(Long review_id) {
        try {
            Review restorereview = reviewRepository.getReferenceById(review_id);
            List<Review> reviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
            if (reviewList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no reviews created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (Review review : reviewList) {
                if (review.equals(restorereview) && review.getStatus().equals(Status.INACTIVE)) {
                    review.setStatus(Status.ACTIVE);
                    reviewRepository.save(review);

                    ReviewResponse reviewResponse = ReviewMapper.toReviewResponse(review);
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Restore review Successfully")
                            .data(reviewResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Review not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> RemoveReview(Long review_id) {
        try {
            Review removereview = reviewRepository.getReferenceById(review_id);
            List<Review> reviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));
            if (reviewList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no reviews created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (Review review : reviewList) {
                if (review.equals(removereview)) {

                    reviewRepository.delete(review);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Remove review Successfully")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Review not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }
}
