package org.petspa.petcaresystem.pet.controller;


import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
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
    @PutMapping("/pet/{id}")
    public ResponseEntity<ResponseObj> UpdatePetProflie(@PathVariable String pet_id,
                                                        @RequestBody UpdatePetRequest petRequest){
        return petService.UpdatePetProflie(pet_id, petRequest);
    }
}
