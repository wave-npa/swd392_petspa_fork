package org.petspa.petcaresystem.review.service.implement;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.review.model.entity.Review;
import org.petspa.petcaresystem.review.model.request.UpdateReviewRequestDTO;
import org.petspa.petcaresystem.review.model.response.ResponseInfor;
import org.petspa.petcaresystem.review.model.response.ReviewResponseDTO;
import org.petspa.petcaresystem.review.model.response.ReviewResponseData;
import org.petspa.petcaresystem.review.repository.ReviewRepository;
import org.petspa.petcaresystem.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private static final String format_pattern = "yyyy-MM-dd HH:mm";

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AuthenUserRepository authenUserRepository;

    @Override
    public ReviewResponseDTO findAllReview() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Review found";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        List<ReviewResponseData> reviewResponseDataList = new ArrayList<>();

        responseInfor.setMessage(message);
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try{

            List<Review> reviewList = reviewRepository.findAll();
            if(reviewList == null){
                message = "Review not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO(responseInfor,null, reviewResponseDataList);
            }

            for(Review review : reviewList){
                ReviewResponseData reviewResponseData = new ReviewResponseData();
                reviewResponseData.setReviewId(review.getReviewId());
                reviewResponseData.setDescription(reviewResponseData.getDescription());
                reviewResponseData.setReviewRating(reviewResponseData.getReviewRating());
                reviewResponseData.setStatus(review.getStatus());
                reviewResponseData.setUserId(review.getAuthor().getUserId());

                reviewResponseDataList.add(reviewResponseData);
            }

        }catch (Exception e){
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ReviewResponseDTO(responseInfor,null, reviewResponseDataList);
    }

    @Override
    public ReviewResponseDTO findReviewByAuthor(Long userId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Review found";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        List<ReviewResponseData> reviewResponseDataList = new ArrayList<>();

        responseInfor.setMessage(message);
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try{

            AuthenUser authenUser = authenUserRepository.findByUserId(userId);
            if(authenUser == null){
                message = "User not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO(responseInfor,null, null);
            }
            List<Review> reviewList = reviewRepository.findByAuthor(authenUser);
            if(reviewList == null){
                message = "Review not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setTimeStamp(timeStamp);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ReviewResponseDTO(responseInfor,null, null);
            }

            for(Review review : reviewList){
                ReviewResponseData reviewResponseData = new ReviewResponseData();
                reviewResponseData.setReviewId(review.getReviewId());
                reviewResponseData.setDescription(review.getDescription());
                reviewResponseData.setReviewRating(review.getRating());
                reviewResponseData.setStatus(review.getStatus());
                reviewResponseData.setUserId(review.getAuthor().getUserId());

                reviewResponseDataList.add(reviewResponseData);
            }

        }catch (Exception e){
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ReviewResponseDTO(responseInfor,null, reviewResponseDataList);
    }

    @Override
    public ResponseInfor updateStatusReview(Long reviewId, UpdateReviewRequestDTO updateReviewRequestDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Review updated successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        try{

            Review review = reviewRepository.findByReviewId(reviewId);
            if(review == null){
                message = "Review not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                return new ResponseInfor(message, timeStamp, statusCode, statusValue);
            }

            review.setDescription(updateReviewRequestDTO.getDescription());
            review.setRating(updateReviewRequestDTO.getRating());
            review.setStatus(updateReviewRequestDTO.getStatus());

            reviewRepository.save(review);


        }catch (Exception e){
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseInfor(message, timeStamp, statusCode, statusValue);
    }
}
