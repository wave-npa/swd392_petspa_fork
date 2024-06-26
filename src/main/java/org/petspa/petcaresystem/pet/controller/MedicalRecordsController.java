package org.petspa.petcaresystem.pet.controller;

import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.model.request.CreateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.model.request.UpdateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class MedicalRecordsController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping(value = {"/medicalrecord/{pet_id}/viewMedicalrecord"})
    public ResponseEntity<ResponseObj> ViewListPetMedicalRecordbyPetId(@PathVariable Long pet_id){
        return medicalRecordService.ViewListPetMedicalRecordbyPetId(pet_id);
    }

    @GetMapping(value = {"/medicalrecord/viewListMedicalrecord"})
    ResponseEntity<ResponseObj> ViewListAllMedicalRecord(){
        return medicalRecordService.ViewListAllMedicalRecord();
    }

    @PostMapping("/medicalrecord/{pet_id}/create")
    public ResponseEntity<ResponseObj> CreateMedicalRecord(@PathVariable Long pet_id,
                                                           @RequestBody CreateMedicalRecordRequest MedicalRecordRequest) {
        return medicalRecordService.CreateMedicalRecord(pet_id, MedicalRecordRequest);
    }

    @PutMapping("/medicalrecord/update")
    public ResponseEntity<ResponseObj> UpdateMedicalRecord(@RequestParam Long medicalrecord_id,
                                                           @RequestBody UpdateMedicalRecordRequest MedicalRecordRequest) {
        return medicalRecordService.UpdateMedicalRecord(medicalrecord_id, MedicalRecordRequest);
    }

    @PutMapping("/medicalrecord/delete")
    public ResponseEntity<ResponseObj> DeleteMedicalRecord(@RequestParam Long medicalrecord_id) {
        return medicalRecordService.DeleteMedicalRecord(medicalrecord_id);
    }

    @PutMapping("/medicalrecord/restore")
    public ResponseEntity<ResponseObj> RestoreMedicalRecord(@RequestParam Long medicalrecord_id) {
        return medicalRecordService.RestoreMedicalRecord(medicalrecord_id);
    }
}
