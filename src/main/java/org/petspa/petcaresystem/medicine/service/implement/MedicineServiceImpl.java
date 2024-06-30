package org.petspa.petcaresystem.medicine.service.implement;


import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.mapper.MedicineMapper;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.medicine.model.request.CreateMedicineRequest;
import org.petspa.petcaresystem.medicine.model.request.UpdateMedicineRequest;
import org.petspa.petcaresystem.medicine.model.response.MedicineResponse;
import org.petspa.petcaresystem.medicine.repository.MedicineRepository;
import org.petspa.petcaresystem.medicine.service.MedicineService;
import org.petspa.petcaresystem.pet.model.entity.MedicalRecord;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService{

    @Autowired
    private MedicineRepository medicineRepository;
    private MedicalRecordRepository recordRepository;

    @Override
    public ResponseEntity<ResponseObj> ViewMedicineByRecordId(Long medicalrecord_id) {
        try {
            MedicalRecord medicalRecord = recordRepository.getReferenceById(medicalrecord_id);
            List<MedicalRecord> medicalRecordList = recordRepository.findAll();

            for (MedicalRecord record : medicalRecordList) {
                if (record.getStatus().equals(Status.ACTIVE) && record.equals(medicalRecord)) {

                    Collection<Medicine> medicine = record.getPetMedicine();
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Load Medicine Successfully")
                            .data(medicine)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Medical record not found")
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
    public ResponseEntity<ResponseObj> ViewAllMedicine() {
        try {
            List<Medicine> medicineList = medicineRepository.findAll();

            if (medicineList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Medicine List is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Load Medical Record Successfully")
                    .data(medicineList)
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
    public ResponseEntity<ResponseObj> CreateMedicine(Long medicalrecord_id, CreateMedicineRequest medicineRequest) {
        try {
            MedicalRecord medicalRecord = recordRepository.getReferenceById(medicalrecord_id);
            List<MedicalRecord> medicalRecordList = recordRepository.findAll();
            Medicine medicine = new Medicine();
            for (MedicalRecord record : medicalRecordList) {
                if (record.getStatus().equals(Status.ACTIVE) && record.equals(medicalRecord)) {

                    medicine.setMedicine_name(medicineRequest.getMedicineName());

                    medicine.setPrice(medicineRequest.getPrice());

                    medicine.setStatus(Status.ACTIVE);

                    Collection<MedicalRecord> medicalRecords = new ArrayList<>();
                    medicalRecords.add(record);
                    medicine.setMedicalRecord(medicalRecords);

                    Medicine createMedicine = medicineRepository.save(medicine);

                    MedicineResponse medicineResponse = MedicineMapper.toMedicineResponse(createMedicine);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Create Medicine Successfully")
                            .data(medicineResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Medical record not found")
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
    public ResponseEntity<ResponseObj> UpdateMedicine(Long medicine_id, UpdateMedicineRequest medicineRequest) {
        try {
            Medicine medicineUpdate = medicineRepository.getReferenceById(medicine_id);
            List<Medicine> medicineList = medicineRepository.findAll();
            for (Medicine medicine : medicineList) {
                if (medicine.getStatus().equals(Status.ACTIVE) && medicine.equals(medicineUpdate)) {

                    medicine.setMedicine_name(medicineRequest.getMedicineName());

                    medicine.setPrice(medicineRequest.getPrice());

                    medicine.setStatus(medicineRequest.getStatus());

                    //tìm record muốn cập nhật
                    Collection<MedicalRecord> recordUpdate = medicineRequest.getMedicalRecord();
                    Collection<MedicalRecord> medicalrecordListUpdated = new ArrayList<>();
                    //ban dau tao 1 list voi all record deu chung 1 medicine
                    List<MedicalRecord> medicalRecordList = recordRepository.findAll();
                    for (MedicalRecord record2 : medicalRecordList)
                        if (record2.getPetMedicine().equals(medicine)) {
                            medicalrecordListUpdated.add(record2);
                        }
                    //loc cai list muon cap nhat
                    for (MedicalRecord record : recordUpdate) {
                        if (record.getPetMedicine().equals(medicine)) {
                            //add cái record mới được cập nhật vào
                            medicalrecordListUpdated.add(record);
                        }
                    }
                    medicine.setMedicalRecord(medicalrecordListUpdated);

                    Medicine updateMedicine = medicineRepository.save(medicine);

                    MedicineResponse medicineResponse = MedicineMapper.toMedicineResponse(updateMedicine);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Update Medicine Successfully")
                            .data(medicineResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("medicine not found")
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
    public ResponseEntity<ResponseObj> DeleteMedicine(Long medicine_id) {
        try {
            Medicine medicineDelete = medicineRepository.getReferenceById(medicine_id);
            List<Medicine> medicineList = medicineRepository.findAll();
            for (Medicine medicine : medicineList) {
                if (medicine.getStatus().equals(Status.ACTIVE) && medicine.equals(medicineDelete)) {

                    medicine.setStatus(Status.INACTIVE);

                    medicineRepository.save(medicine);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Delete Medicine Successfully")
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("medicine not found")
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
    public ResponseEntity<ResponseObj> RestoreMedicine(Long medicine_id) {
        try {
            Medicine medicineDelete = medicineRepository.getReferenceById(medicine_id);
            List<Medicine> medicineList = medicineRepository.findAll();
            for (Medicine medicine : medicineList) {
                if (medicine.getStatus().equals(Status.INACTIVE) && medicine.equals(medicineDelete)) {

                    medicine.setStatus(Status.ACTIVE);

                    Medicine restoreMedicine = medicineRepository.save(medicine);
                    MedicineResponse medicineResponse = MedicineMapper.toMedicineResponse(restoreMedicine);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Restore Medicine Successfully")
                            .data(medicineResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("medicine not found")
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
