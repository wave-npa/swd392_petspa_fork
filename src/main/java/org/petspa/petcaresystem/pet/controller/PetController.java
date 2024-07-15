package org.petspa.petcaresystem.pet.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.request.CreatePetRequest;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/petspa/pet")
@CrossOrigin
@Tag(name = "pet", description = "pet Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Pet.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class PetController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private PetService petService;

    @GetMapping(value = {"/viewPet"})
    public ResponseEntity<ResponseObj> ViewPetProfliebyId(@RequestParam Long pet_id){
        return petService.ViewPetProfliebyId(pet_id);
    }

    @GetMapping(value = {"/{cus_id}/ownPet"})
    public ResponseEntity<ResponseObj> ViewListPetProfliebyOwnerId(@PathVariable Long cus_id){
        return petService.ViewListPetProfliebyOwnerId(cus_id);
    }

    @GetMapping(value = {"/viewListPet"})
    public ResponseEntity<ResponseObj> ViewListAllPetProflie(){
        return petService.ViewListAllPetProflie();
    }

    @GetMapping(value = {"/viewListPet/sortbySpecies"})
    ResponseEntity<ResponseObj> SortPetbySpecies(@RequestParam Species species){
        return petService.SortPetbySpecies(species);
    };

    @GetMapping(value = {"/viewListPet/sortbyBreed"})
    ResponseEntity<ResponseObj> SortPetbyBreed(@RequestParam String breed){
        return petService.SortPetbyBreed(breed);
    };

    @GetMapping(value = {"/viewListPet/sortbyStatus"})
    ResponseEntity<ResponseObj> SortPetbyStatus(@RequestParam Status status){
        return petService.SortPetbyStatus(status);
    };

    @PostMapping("/{cus_id}/create")
    public ResponseEntity<ResponseObj> CreatePetProflie(@PathVariable Long cus_id,
                                                        @RequestBody CreatePetRequest petRequest){
        return petService.CreatePetProflie(cus_id, petRequest);
    }

    @PutMapping("/update" )
    public ResponseEntity<ResponseObj> UpdatePetProflie(@RequestParam Long pet_id,
                                                        @RequestBody UpdatePetRequest petRequest){
        return petService.UpdatePetProflie(pet_id, petRequest);
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseObj> DeletePetProflie(@RequestParam Long pet_id){
        return petService.DeletePetProflie(pet_id);
    }

    @PutMapping("/restore")
    public ResponseEntity<ResponseObj> RestorePetProflie(@RequestParam Long pet_id){
        return petService.RestorePetProflie(pet_id);
    }
}
