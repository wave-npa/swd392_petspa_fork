package org.petspa.petcaresystem.pet.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;

import java.util.Collection;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PetResponse {
    Long pet_id;
    String pet_name;
    int age;
    Gender gender;
    Species species;
    String type_of_species;
    AuthenUser owner;
    Collection<MedicalRecord> medicalRecords;
    Status status;
}
