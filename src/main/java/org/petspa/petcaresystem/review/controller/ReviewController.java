package org.petspa.petcaresystem.review.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.review.model.entity.Review;
import org.petspa.petcaresystem.review.model.request.UpdateReviewRequestDTO;
import org.petspa.petcaresystem.review.model.response.ResponseInfor;
import org.petspa.petcaresystem.review.model.response.ReviewResponseDTO;
import org.petspa.petcaresystem.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/petspa/review")
@CrossOrigin
@Tag(name = "review", description = "Review Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Review.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = {"/findAllReview"})
    public ReviewResponseDTO findAllReview(){
        ReviewResponseDTO reviewResponseDTO = reviewService.findAllReview();
        return reviewResponseDTO;
    }

    @GetMapping(value = {"/findReviewByAuthorId/{authorId}"})
    public ReviewResponseDTO findReviewByAuthorId(@PathVariable Long authorId){
        ReviewResponseDTO reviewResponseDTO = reviewService.findReviewByAuthor(authorId);
        return reviewResponseDTO;
    }

    @PutMapping(value = {"/update/{reviewId}"})
    public ResponseInfor updateReview(@PathVariable(value = "reviewId") Long reviewId,
                                            @RequestBody UpdateReviewRequestDTO updateReviewRequestDTO){
        ResponseInfor responseInfor = reviewService.updateStatusReview(reviewId, updateReviewRequestDTO);
        return responseInfor;
    }

    @GetMapping(value = {"/findReviewByAppointmentId/{appointmentId}"})
    public ReviewResponseDTO findReviewByAppointmentId(@PathVariable(value = "appointmentId") Long appointmentId){
        ReviewResponseDTO reviewResponseDTO = reviewService.findReviewByApppointmentId(appointmentId);
        return reviewResponseDTO;
    }
}
