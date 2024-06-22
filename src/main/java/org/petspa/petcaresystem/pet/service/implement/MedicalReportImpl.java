package org.petspa.petcaresystem.pet.service.implement;

import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.mapper.MedicalRecordMapper;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.request.CreateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.model.request.UpdateMedicalRecordRequest;
import org.petspa.petcaresystem.pet.model.response.MedicalRecordResponse;
import org.petspa.petcaresystem.pet.repository.MedicalRecordRepository;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.petspa.petcaresystem.pet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MedicalReportImpl implements MedicalRecordService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public ResponseEntity<ResponseObj> CreateMedicalRecord(Long pet_id, CreateMedicalRecordRequest MedicalRecordRequest) {
        try {
            Pet pet = petRepository.findById(pet_id).orElse(null);

            if (pet.getStatus() != Status.ACTIVE || pet.equals(null)) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("pet not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            MedicalRecord medicalrecord = new MedicalRecord();

            medicalrecord.setPetId(MedicalRecordRequest.getPet_id());

            medicalrecord.setMedical_description(MedicalRecordRequest.getDesscription());

            medicalrecord.setLast_update(LocalDateTime.now());

            medicalrecord.setStatus(Status.ACTIVE);

            medicalrecord.setMedicines(MedicalRecordRequest.getMedicines());

            MedicalRecord createMedicalRecord = medicalRecordRepository.save(medicalrecord);

            MedicalRecordResponse medicalRecordResponse = MedicalRecordMapper.toMedicalRecordResponse(createMedicalRecord);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Create Medical Record Successfully")
                    .data(medicalRecordResponse)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> UpdateMedicalRecord(Long medicalrecord_id, UpdateMedicalRecordRequest MedicalRecordRequest) {
        try {
            MedicalRecord medicalrecord = medicalRecordRepository.findById(medicalrecord_id).orElse(null);

            if (medicalrecord.equals(null)) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("medical record not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }

            medicalrecord.setMedical_description(MedicalRecordRequest.getDesscription());

            medicalrecord.setMedicines(MedicalRecordRequest.getMedicines());

            medicalrecord.setLast_update(MedicalRecordRequest.getUpdateTime());

            medicalrecord.setStatus(MedicalRecordRequest.getStatus());

            MedicalRecord updateMedicalRecord = medicalRecordRepository.save(medicalrecord);

            MedicalRecordResponse medicalRecordResponse = MedicalRecordMapper.toMedicalRecordResponse(updateMedicalRecord);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Update Medical Record Successfully")
                    .data(medicalRecordResponse)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }
}
