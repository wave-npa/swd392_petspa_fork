package org.petspa.petcaresystem.review.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.ReviewRating;
import org.petspa.petcaresystem.enums.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponseData {
    private Long reviewId;
    private String description;
    private Integer reviewRating;
    private Status status;
    private Long userId;
}
