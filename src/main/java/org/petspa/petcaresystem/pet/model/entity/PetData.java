package org.petspa.petcaresystem.pet.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.enums.Status;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PetData {
    private Long petId;
    private String pet_name;
    private int age;
    private Gender gender;
    private Species species;
    private String type_of_species;
    private Status status;
    private Long ownerId;
}
