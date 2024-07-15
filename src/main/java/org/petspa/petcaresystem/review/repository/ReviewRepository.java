package org.petspa.petcaresystem.review.repository;

import org.petspa.petcaresystem.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>{
    public Review findByReviewId(Long reviewID);

}
