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
    public ResponseEntity<ResponseObj> CreateShelter(@RequestBody CreateShelterRequest shelterRequest){
        return shelterService.CreateShelter(shelterRequest);
    }

    @PutMapping("/shelter/update" )
    public ResponseEntity<ResponseObj> UpdateShelter(@RequestParam Long shelter_id,
                                                     @RequestBody UpdateShelterRequest shelterRequest){
        return shelterService.UpdateShelter(shelter_id, shelterRequest);
    }

    @PutMapping("/shelter/delete" )
    ResponseEntity<ResponseObj> DeleteShelter(@RequestParam Long shelter_id){
        return shelterService.DeleteShelter(shelter_id);
    }

    @PutMapping("/shelter/restore" )
    public ResponseEntity<ResponseObj> RestoreShelter(@RequestParam Long shelter_id){
        return shelterService.RestoreShelter(shelter_id);
    }
}
