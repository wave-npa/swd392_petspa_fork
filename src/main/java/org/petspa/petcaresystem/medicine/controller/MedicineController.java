package org.petspa.petcaresystem.medicine.controller;

import org.petspa.petcaresystem.medicine.model.request.CreateMedicineRequest;
import org.petspa.petcaresystem.medicine.model.request.UpdateMedicineRequest;
import org.petspa.petcaresystem.medicine.service.MedicineService;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Controller
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping(value = {"/medicine/{medicalrecord_id}/ViewMedicine"})
    public ResponseEntity<ResponseObj> ViewMedicineByRecordId(@PathVariable Long medicalrecord_id){
        return medicineService.ViewMedicineByRecordId(medicalrecord_id);
    }

    @GetMapping(value = {"/medicine/ViewListMedicine"})
    public ResponseEntity<ResponseObj> ViewAllMedicine(){
        return medicineService.ViewAllMedicine();
    }

    @PostMapping("medicine/{medicalrecord_id}/create")
    public ResponseEntity<ResponseObj> CreateMedicine(@PathVariable Long medicalrecord_id,
                                                      @RequestBody CreateMedicineRequest medicineRequest){
        return medicineService.CreateMedicine(medicalrecord_id, medicineRequest);
    }

    @PutMapping("/medicine/update")
    ResponseEntity<ResponseObj> UpdateMedicine(Long medicine_id, UpdateMedicineRequest medicineRequest){
        return medicineService.UpdateMedicine(medicine_id, medicineRequest);
    }

    @PutMapping("/medicine/delete")
    ResponseEntity<ResponseObj> DeleteMedicine(Long medicine_id){
        return medicineService.DeleteMedicine(medicine_id);
    }

    @PutMapping("/medicine/restore")
    ResponseEntity<ResponseObj> RestoreMedicine(Long medicine_id){
        return medicineService.RestoreMedicine(medicine_id);
    }
}
