package org.petspa.petcaresystem.shelter.controller;

import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.shelter.model.request.CreateShelterRequest;
import org.petspa.petcaresystem.shelter.model.request.UpdateShelterRequest;
import org.petspa.petcaresystem.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class ShelterController {

    @Autowired
    private ShelterService shelterService;

    @GetMapping(value = {"/shelter/viewAllShelter"})
    public ResponseEntity<ResponseObj> ViewAllShelter(){
        return shelterService.ViewAllShelter();
    }

    @GetMapping(value = {"/shelter/viewAllAvailableShelter"})
    public ResponseEntity<ResponseObj> ViewShelterAvailable(){
        return shelterService.ViewShelterAvailable();
    }

    @GetMapping(value = {"/shelter/viewAllUsingShelter"})
    public ResponseEntity<ResponseObj> ViewShelterUsing(){
        return shelterService.ViewShelterUsing();
    }

    @PostMapping("/shelter/create" )
    public ResponseEntity<ResponseObj> CreateShelter(CreateShelterRequest shelterRequest){
        return shelterService.CreateShelter(shelterRequest);
    }

    @PutMapping("/shelter/update" )
    public ResponseEntity<ResponseObj> UpdateShelter(Long shelter_id, UpdateShelterRequest shelterRequest){
        return shelterService.UpdateShelter(shelter_id, shelterRequest);
    }

    @PutMapping("/shelter/delete" )
    public ResponseEntity<ResponseObj> DeleteShelter(Long shelter_id){
        return shelterService.DeleteShelter(shelter_id);
    }

    @PutMapping("/shelter/restore" )
    public ResponseEntity<ResponseObj> RestoreShelter(Long shelter_id){
        return shelterService.RestoreShelter(shelter_id);
    }
}
