package org.petspa.petcaresystem.pet.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdatePetRequest {
    String pet_name;
    int age;
    Gender gender;
    String species;
    String type_of_species;
    Status status;
}
