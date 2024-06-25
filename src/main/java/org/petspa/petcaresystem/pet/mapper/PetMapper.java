package org.petspa.petcaresystem.pet.mapper;

import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.response.PetResponse;

public class PetMapper {
    public static PetResponse toPetResponse(Pet pet){
        return PetResponse.builder()
                .pet_name(pet.getPet_name())
                .age(pet.getAge())
                .gender(pet.getGender())
                .species(pet.getSpecies())
                .type_of_species(pet.getType_of_species())
                .status(pet.getStatus())
                .build();
    }
}
