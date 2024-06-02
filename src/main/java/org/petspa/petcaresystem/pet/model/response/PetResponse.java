package org.petspa.petcaresystem.pet.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetResponse {
    String pet_name;
    int age;
    Gender gender;
    String species;
    String type_of_species;
    Status status;
}
