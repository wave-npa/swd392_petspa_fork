package org.petspa.petcaresystem.pet.controller;


import org.petspa.petcaresystem.pet.model.request.CreatePetRequest;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class PetController {
    @Autowired
    PetService petService;

        @GetMapping(value = {"/pet/viewPet"})
    public ResponseEntity<ResponseObj> ViewPetProfliebyId(@RequestParam Long pet_id){
        return petService.ViewPetProfliebyId(pet_id);
    }

    @GetMapping(value = {"/pet/{cus_id}/ownPet"})
    public ResponseEntity<ResponseObj> ViewListPetProfliebyOwnerId(@PathVariable  Long cus_id){
        return petService.ViewListPetProfliebyOwnerId(cus_id);
    }

    @GetMapping(value = {"/pet/viewListPet"})
    public ResponseEntity<ResponseObj> ViewListAllPetProflie(){
        return petService.ViewListAllPetProflie();
    }

    @PostMapping("/pet/{cus_id}/create")
    public ResponseEntity<ResponseObj> CreatePetProflie(@PathVariable Long cus_id,
                                                        @RequestBody CreatePetRequest petRequest){
        return petService.CreatePetProflie(cus_id, petRequest);
    }

    @PutMapping("/pet/update" )
    public ResponseEntity<ResponseObj> UpdatePetProflie(@RequestParam Long pet_id,
                                                        @RequestBody UpdatePetRequest petRequest){
        return petService.UpdatePetProflie(pet_id, petRequest);
    }

    @DeleteMapping("/pet/delete")
    public ResponseEntity<ResponseObj> DeletePetProflie(@RequestParam Long pet_id){
        return petService.DeletePetProflie(pet_id);
    }
}
