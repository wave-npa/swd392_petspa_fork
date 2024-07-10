package org.petspa.petcaresystem.shelter.service;


import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.shelter.model.request.CreateShelterRequest;
import org.petspa.petcaresystem.shelter.model.request.UpdateShelterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ShelterService {
    ResponseEntity<ResponseObj> ViewAllShelter();
    ResponseEntity<ResponseObj> ViewShelterAvailable();
    ResponseEntity<ResponseObj> ViewShelterUsing();
    ResponseEntity<ResponseObj> CreateShelter(CreateShelterRequest shelterRequest);
    ResponseEntity<ResponseObj> UpdateShelter(Long shelter_id, UpdateShelterRequest shelterRequest);
    ResponseEntity<ResponseObj> DeleteShelter(Long shelter_id);
    ResponseEntity<ResponseObj> RestoreShelter(Long shelter_id);
}
