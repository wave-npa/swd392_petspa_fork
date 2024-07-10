package org.petspa.petcaresystem.boarding.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;

import java.time.LocalDateTime;
import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BoardingResponse {
    Long boardingId;
    LocalDateTime boardingTime;
    Status status;
    Appointment appointment;
    Collection<BoardingDetail> boardingDetail;
    Shelter shelter;
}
