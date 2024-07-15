package org.petspa.petcaresystem.shelter.service;


import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;
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
    ResponseEntity<ResponseObj> CreateShelter(String shelterName, Status status);
    ResponseEntity<ResponseObj> UpdateShelter(Long shelter_id, String name, Status status, ShelterStatus shelterStatus);
    ResponseEntity<ResponseObj> getShelterById(Long shelterId);
}
