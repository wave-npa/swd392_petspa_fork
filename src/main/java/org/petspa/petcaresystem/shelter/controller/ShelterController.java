package org.petspa.petcaresystem.shelter.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.petspa.petcaresystem.shelter.model.request.CreateShelterRequest;
import org.petspa.petcaresystem.shelter.model.request.UpdateShelterRequest;
import org.petspa.petcaresystem.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/petspa/shelter")
@CrossOrigin
@Tag(name = "shelter", description = "shelter Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Shelter.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ShelterController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private ShelterService shelterService;

    @GetMapping(value = {"/viewAllShelter"})
    public ResponseEntity<ResponseObj> ViewAllShelter(){
        return shelterService.ViewAllShelter();
    }

    @GetMapping(value = {"/viewAllAvailableShelter"})
    public ResponseEntity<ResponseObj> ViewShelterAvailable(){
        return shelterService.ViewShelterAvailable();
    }

    @GetMapping(value = {"/viewAllUsingShelter"})
    public ResponseEntity<ResponseObj> ViewShelterUsing(){
        return shelterService.ViewShelterUsing();
    }

    @GetMapping("/getShelter/{shelterId}")
    public ResponseEntity<ResponseObj> getShelter(@PathVariable(value = "shelterId") Long shelterId){
        return shelterService.getShelterById(shelterId);
    }

    @PostMapping("/create" )
    public ResponseEntity<ResponseObj> CreateShelter(@RequestParam(value = "shelter name") String shelterName,
                                                     @RequestParam(value = "status")Status status){
        return shelterService.CreateShelter(shelterName, status);
    }

    @PutMapping("/update" )
    public ResponseEntity<ResponseObj> UpdateShelter(@RequestParam(value = "shelter id") Long shelter_id,
                                                     @RequestParam(value = "shelterName") String shelterName,
                                                     @RequestParam(value = "status") Status status,
                                                     @RequestParam(value = "shelter status")ShelterStatus shelterStatus){
        return shelterService.UpdateShelter(shelter_id, shelterName, status, shelterStatus);
    }
}
