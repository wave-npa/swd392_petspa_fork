package org.petspa.petcaresystem.review.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponseDTO {
    private ResponseInfor infor;
    private ReviewResponseData data;
    private List<ReviewResponseData> listData;
}
