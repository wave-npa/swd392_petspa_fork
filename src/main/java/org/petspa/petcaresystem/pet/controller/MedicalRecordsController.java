package org.petspa.petcaresystem.pet.controller;

import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.model.request.CreateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.model.request.UpdateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.repository.MedicalRecordRepository;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.petspa.petcaresystem.pet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/medicalrecord-management")
public class MedicalRecordsController {

    @Autowired
    PetRepository petRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @Qualifier("medicalRecordService")
    @Autowired
    private MedicalRecordService medicalRecordService;

    @PostMapping(value = {"/medicalrecord/create"})
    public ResponseEntity<ResponseObj> CreateMedicalRecord(@PathVariable Long pet_id,
                                                           @RequestBody CreateMedicalRecordRequest MedicalRecordRequest) {
        return medicalRecordService.CreateMedicalRecord(pet_id, MedicalRecordRequest);
    }

    @PutMapping(value = {"/medicalrecord/update"})
    public ResponseEntity<ResponseObj> UpdateMedicalRecord(@PathVariable Long medicalrecord_id,
                                                           @RequestBody UpdateMedicalRecordRequest MedicalRecordRequest) {
        return medicalRecordService.UpdateMedicalRecord(medicalrecord_id, MedicalRecordRequest);
    }
}
