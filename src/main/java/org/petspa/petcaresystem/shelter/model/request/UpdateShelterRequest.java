package org.petspa.petcaresystem.shelter.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateShelterRequest {
    String shelterName;
    ShelterStatus shelterStatus;
    Status status;
}
