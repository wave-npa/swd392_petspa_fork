package org.petspa.petcaresystem.pet.service.implement;

import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.medicine.repository.MedicineRepository;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
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
import java.util.*;

@Service
public class MedicalReportImpl implements MedicalRecordService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;
    @Autowired
    MedicineRepository medicineRepository;

    @Override
    public ResponseEntity<ResponseObj> ViewListPetMedicalRecordbyPetId(Long pet_id) {
        try {
            Pet pet = petRepository.getReferenceById(pet_id);
            List<Pet> petList = petRepository.findAll();
            for (Pet pet1 : petList) {
                if (pet1.getStatus().equals(Status.ACTIVE) && pet1.equals(pet)) {
                    if (pet1.getMedicalRecord().isEmpty()) {
                        ResponseObj responseObj = ResponseObj.builder()
                                .message("The pet's medical record list is empty")
                                .data(null)
                                .build();
                        return ResponseEntity.ok().body(responseObj);
                    } else {
                        Collection<MedicalRecord> medicalRecordrepo = pet1.getMedicalRecord();
                        List<MedicalRecordResponse> medicalRecordList = new ArrayList<>();
                        for (MedicalRecord record : medicalRecordrepo) {
                            if (record.getStatus().equals(Status.ACTIVE)) {
                                MedicalRecordResponse recordResponse = MedicalRecordMapper.toMedicalRecordResponse(record);
                                medicalRecordList.add(recordResponse);
                            }
                        }
                        ResponseObj responseObj = ResponseObj.builder()
                                    .message("Load Medical Record Successfully")
                                    .data(medicalRecordList)
                                    .build();
                        return ResponseEntity.ok().body(responseObj);
                    }
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("pet not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

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
    public ResponseEntity<ResponseObj> ViewListAllMedicalRecord() {
        try {

            List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();

            if (medicalRecordList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Medical Record List is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            }
            List<MedicalRecordResponse> medicalRecordResponseList = new ArrayList<>();
            for (MedicalRecord record : medicalRecordList) {
                MedicalRecordResponse recordResponse = MedicalRecordMapper.toMedicalRecordResponse(record);
                medicalRecordResponseList.add(recordResponse);
            }
            ResponseObj responseObj = ResponseObj.builder()
                        .message("Load Medical Record Successfully")
                        .data(medicalRecordList)
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
    public ResponseEntity<ResponseObj> CreateMedicalRecord(Long pet_id, CreateMedicalRecordRequest MedicalRecordRequest) {
        try {
            Pet pet = petRepository.getReferenceById(pet_id);
            List<Pet> petList = petRepository.findAll();
            MedicalRecord medicalrecord = new MedicalRecord();
            for (Pet pet1 : petList) {
                if (pet1.getStatus().equals(Status.ACTIVE) && pet1.equals(pet)) {

                    medicalrecord.setPet(pet1);

                    medicalrecord.setMedical_description(MedicalRecordRequest.getDescription());

                    medicalrecord.setLast_update(LocalDateTime.now());

                    medicalrecord.setStatus(Status.ACTIVE);

                    medicalrecord.setPetMedicine(MedicalRecordRequest.getMedicines());

                    MedicalRecord createMedicalRecord = medicalRecordRepository.save(medicalrecord);

                    MedicalRecordResponse medicalRecordResponse = MedicalRecordMapper.toMedicalRecordResponse(createMedicalRecord);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Create Medical Record Successfully")
                            .data(medicalRecordResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("pet not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

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
            MedicalRecord medicalrecordUpdate = medicalRecordRepository.getReferenceById(medicalrecord_id);
            List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();

            for (MedicalRecord medicalrecord : medicalRecordList) {
                if (medicalrecord.equals(medicalrecordUpdate) && medicalrecord.getStatus().equals(Status.ACTIVE)) {

                    medicalrecord.setMedical_description(MedicalRecordRequest.getDescription());

                    //liet ke list muon cap nhat va tao 1 list de cap nhat
                    Collection<Medicine> medicineUpdate = MedicalRecordRequest.getMedicines();
                    Collection<Medicine> medicineListUpdate = new ArrayList<>();
                    //so sanh tat ca cac medicine muoon cap nhat voi bang medicine xem co ton tai hay ko
                    Collection<Medicine> medicineList = medicineRepository.findAll();
                    for (Medicine medicine : medicineUpdate) {
                        for (Medicine medicine2 : medicineList) {
                            if (medicine.equals(medicine2)){
                                //neu co thi ca nhat vao list muon cap nhat
                                medicineListUpdate.add(medicine);
                            }
                        }
                    }
                    medicalrecord.setPetMedicine(medicineListUpdate);

                    medicalrecord.setLast_update(MedicalRecordRequest.getUpdateTime());

                    MedicalRecord updateMedicalRecord = medicalRecordRepository.save(medicalrecord);

                    MedicalRecordResponse medicalRecordResponse = MedicalRecordMapper.
                            toMedicalRecordResponse(updateMedicalRecord);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Update Medical Record Successfully")
                            .data(medicalRecordResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);

                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("medical record not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

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
    public ResponseEntity<ResponseObj> DeleteMedicalRecord(Long medicalrecord_id) {
        try {
            MedicalRecord medicalrecorddelete = medicalRecordRepository.getReferenceById(medicalrecord_id);
            List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();

            for (MedicalRecord medicalrecord : medicalRecordList) {
                if (medicalrecord.equals(medicalrecorddelete) && medicalrecord.getStatus().equals(Status.ACTIVE)) {

                    medicalrecord.setStatus(Status.INACTIVE);

                    medicalRecordRepository.save(medicalrecord);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Delete Medical Record Successfully")
                            .build();
                    return ResponseEntity.ok().body(responseObj);

                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("medical record not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

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
    public ResponseEntity<ResponseObj> RestoreMedicalRecord(Long medicalrecord_id) {
        try {
            MedicalRecord medicalrecordrestore = medicalRecordRepository.getReferenceById(medicalrecord_id);
            List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();

            for (MedicalRecord medicalrecord : medicalRecordList) {
                if (medicalrecord.equals(medicalrecordrestore) && medicalrecord.getStatus().equals(Status.INACTIVE)) {

                    medicalrecord.setStatus(Status.ACTIVE);

                    MedicalRecord restoreMedicalRecord = medicalRecordRepository.save(medicalrecord);
                    MedicalRecordResponse medicalRecordResponse = MedicalRecordMapper
                            .toMedicalRecordResponse(restoreMedicalRecord);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Restore Medical Record Successfully")
                            .data(medicalRecordResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);

                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("medical record not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);

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
