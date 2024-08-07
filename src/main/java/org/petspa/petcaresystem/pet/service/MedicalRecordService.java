package org.petspa.petcaresystem.pet.service;

import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.model.request.CreateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.model.request.UpdateMedicalRecordRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MedicalRecordService {
    ResponseEntity<ResponseObj> ViewListPetMedicalRecordbyPetId(Long pet_id);
    ResponseEntity<ResponseObj> ViewListAllMedicalRecord();
    ResponseEntity<ResponseObj> CreateMedicalRecord(Long pet_id, CreateMedicalRecordRequest MedicalRecordRequest);
    ResponseEntity<ResponseObj> UpdateMedicalRecord(Long medicalrecord_id, UpdateMedicalRecordRequest MedicalRecordRequest);
    ResponseEntity<ResponseObj> DeleteMedicalRecord(Long medicalrecord_id);
}
