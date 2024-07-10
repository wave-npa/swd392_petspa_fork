package org.petspa.petcaresystem.shelter.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateShelterRequest {
    String shelterName;
}
