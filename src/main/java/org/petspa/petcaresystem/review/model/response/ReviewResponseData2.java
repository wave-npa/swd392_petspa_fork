package org.petspa.petcaresystem.review.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.PetData;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponseData2 {
    private Long reviewId;
    private String description;
    private Integer reviewRating;
    private Status status;
    private Long userId;
    private String fullName;
    private String serviceName;
    private PetData petData;
}
