package org.petspa.petcaresystem.shelter.mapper;

import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.petspa.petcaresystem.shelter.model.response.ShelterResponse;

public class ShelterMapper {
    public static ShelterResponse toShelterResponse(Shelter shelter) {
        return ShelterResponse.builder()
                .shelter_id(shelter.getShelterId())
                .shelter_name(shelter.getShelterName())
                .status(shelter.getStatus())
                .shelterStatus(shelter.getShelterStatus())
                .build();
    }
}
