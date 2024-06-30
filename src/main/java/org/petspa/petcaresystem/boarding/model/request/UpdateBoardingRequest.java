package org.petspa.petcaresystem.boarding.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.shelter.model.Shelter;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateBoardingRequest {
    Status status;
    Collection<BoardingDetail> boardingDetail;
    Shelter shelter;
}
