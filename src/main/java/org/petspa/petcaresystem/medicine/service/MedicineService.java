package org.petspa.petcaresystem.medicine.service;


import org.petspa.petcaresystem.medicine.model.request.CreateMedicineRequest;
import org.petspa.petcaresystem.medicine.model.request.UpdateMedicineRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MedicineService {
    ResponseEntity<ResponseObj> ViewMedicineByRecordId(Long medicalrecord_id);
    ResponseEntity<ResponseObj> ViewAllMedicine();
    ResponseEntity<ResponseObj> CreateMedicine(Long medicalrecord_id, CreateMedicineRequest medicineRequest);
    ResponseEntity<ResponseObj> UpdateMedicine(Long medicine_id, UpdateMedicineRequest medicineRequest);
    ResponseEntity<ResponseObj> DeleteMedicine(Long medicine_id);
    ResponseEntity<ResponseObj> RestoreMedicine(Long medicine_id);
}
