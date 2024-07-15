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
import org.petspa.petcaresystem.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/petspa/review")
@CrossOrigin
@Tag(name = "review", description = "review Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Review.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ReviewController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = {"/ViewAllReview"})
    public ResponseEntity<ResponseObj> ViewAllReview(){
        return reviewService.ViewAllReview();
    }

    @GetMapping(value = {"/ViewReview/{Author_id}"})
    public ResponseEntity<ResponseObj> ViewReviewByAuthor(@PathVariable Long Author_id){
        return reviewService.ViewReviewByAuthor(Author_id);
    }

    @GetMapping(value = {"/ViewAllReview"})
    public ResponseEntity<ResponseObj> SortReviewByRating(@RequestParam ReviewRating rating){
        return reviewService.SortReviewByRating(rating);
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseObj> DeleteReview(Long review_id){
        return reviewService.DeleteReview(review_id);
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseObj> RestoreReview(Long review_id){
        return reviewService.RestoreReview(review_id);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ResponseObj> RemoveReview(Long review_id){
        return reviewService.RemoveReview(review_id);
    }
}
