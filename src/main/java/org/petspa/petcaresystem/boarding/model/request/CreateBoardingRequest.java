package org.petspa.petcaresystem.boarding.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateBoardingRequest {
    Appointment appointment;
    BoardingDetail boardingDetail;
}
