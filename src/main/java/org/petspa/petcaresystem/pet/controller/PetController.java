package org.petspa.petcaresystem.pet.controller;


import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.model.request.CreatePetRequest;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.petspa.petcaresystem.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pet-management")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping(value = {"/pet/create"})
    public ResponseEntity<ResponseObj> CreatePetProflie(@PathVariable Long cus_id,
                                                        @RequestBody CreatePetRequest petRequest){
        return petService.CreatePetProflie(cus_id, petRequest);
    }
    @PutMapping(value = {"/pet/update"})
    public ResponseEntity<ResponseObj> UpdatePetProflie(@PathVariable Long pet_id,
                                                        @RequestBody UpdatePetRequest petRequest){
        return petService.UpdatePetProflie(pet_id, petRequest);
    }
}
