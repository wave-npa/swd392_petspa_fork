package org.petspa.petcaresystem.pet.service;

import org.petspa.petcaresystem.authenuser.model.request.ProfileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PetService {
    ResponseEntity<ResponseObj> UpdatePetProflie(String pet_id, UpdatePetRequest petRequest);
}
