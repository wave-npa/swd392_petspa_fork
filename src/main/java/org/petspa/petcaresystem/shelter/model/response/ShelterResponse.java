package org.petspa.petcaresystem.shelter.model.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;

import java.util.Collection;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShelterResponse {
    Long shelter_id;
    String shelter_name;
    Status status;
    ShelterStatus shelterStatus;
//    Collection<BoardingAppointment> boardings;
}
