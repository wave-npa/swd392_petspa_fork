package org.petspa.petcaresystem.review.repository;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.review.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    public Review findByReviewId(Long reviewID);
    public List<Review> findByAuthor(AuthenUser authenUser);

}
