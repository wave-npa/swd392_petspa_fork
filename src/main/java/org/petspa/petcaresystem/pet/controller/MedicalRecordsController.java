package org.petspa.petcaresystem.pet.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.model.request.CreateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.model.request.UpdateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.service.MedicalRecordService;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/petspa/medicalrecord")
@CrossOrigin
@Tag(name = "medicalrecord", description = "medicalrecord Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = MedicalRecord.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class MedicalRecordsController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(value = {"/{pet_id}/viewMedicalrecord"})
    public ResponseEntity<ResponseObj> ViewListPetMedicalRecordbyPetId(@PathVariable Long pet_id){
        return medicalRecordService.ViewListPetMedicalRecordbyPetId(pet_id);
    }

    @GetMapping(value = {"/viewListMedicalrecord"})
    ResponseEntity<ResponseObj> ViewListAllMedicalRecord(){
        return medicalRecordService.ViewListAllMedicalRecord();
    }

    @PostMapping("/{pet_id}/create")
    public ResponseEntity<ResponseObj> CreateMedicalRecord(@PathVariable Long pet_id,
                                                           @RequestBody CreateMedicalRecordRequest MedicalRecordRequest) {
        return medicalRecordService.CreateMedicalRecord(pet_id, MedicalRecordRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObj> UpdateMedicalRecord(@RequestParam Long medicalrecord_id,
                                                           @RequestBody UpdateMedicalRecordRequest MedicalRecordRequest) {
        return medicalRecordService.UpdateMedicalRecord(medicalrecord_id, MedicalRecordRequest);
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseObj> DeleteMedicalRecord(@RequestParam Long medicalrecord_id) {
        return medicalRecordService.DeleteMedicalRecord(medicalrecord_id);
    }

    @PutMapping("/restore")
    public ResponseEntity<ResponseObj> RestoreMedicalRecord(@RequestParam Long medicalrecord_id) {
        return medicalRecordService.RestoreMedicalRecord(medicalrecord_id);
    }
}
