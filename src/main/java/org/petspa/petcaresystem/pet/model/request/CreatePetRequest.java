package org.petspa.petcaresystem.pet.model.request;


import jakarta.annotation.Nonnull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePetRequest {
    String pet_name;
    int age;
    Gender gender;
    Species species;
    String type_of_species;
}
