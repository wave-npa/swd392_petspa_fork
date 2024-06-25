package org.petspa.petcaresystem.pet.service;


import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.model.request.CreatePetRequest;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PetService {
    ResponseEntity<ResponseObj> ViewPetProfliebyId(Long pet_id);
    ResponseEntity<ResponseObj> ViewListPetProfliebyOwnerId(Long cus_id);
    ResponseEntity<ResponseObj> ViewListAllPetProflie();
    ResponseEntity<ResponseObj> CreatePetProflie(Long cus_id, CreatePetRequest petRequest);
    ResponseEntity<ResponseObj> UpdatePetProflie(Long pet_id, UpdatePetRequest petRequest);
    ResponseEntity<ResponseObj> DeletePetProflie(Long pet_id);
}
