package org.petspa.petcaresystem.review.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateReviewRequestDTO {
    private String description;
    private Integer rating;
    private Status status;
}
