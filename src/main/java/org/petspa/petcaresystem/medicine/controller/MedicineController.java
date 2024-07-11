package org.petspa.petcaresystem.medicine.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.medicine.model.request.CreateMedicineRequest;
import org.petspa.petcaresystem.medicine.model.request.UpdateMedicineRequest;
import org.petspa.petcaresystem.medicine.service.MedicineService;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//@RestController
//@RequestMapping("api")
//@Controller
@RestController
@RequestMapping("/petspa/medicine")
@CrossOrigin
@Tag(name = "medicine", description = "medicine Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Medicine.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class MedicineController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private MedicineService medicineService;

    @GetMapping(value = {"/{medicalrecord_id}/ViewMedicine"})
    public ResponseEntity<ResponseObj> ViewMedicineByRecordId(@PathVariable Long medicalrecord_id){
        return medicineService.ViewMedicineByRecordId(medicalrecord_id);
    }

    @GetMapping(value = {"/ViewListMedicine"})
    public ResponseEntity<ResponseObj> ViewAllMedicine(){
        return medicineService.ViewAllMedicine();
    }

    @PostMapping("/{medicalrecord_id}/create")
    public ResponseEntity<ResponseObj> CreateMedicine(@PathVariable Long medicalrecord_id,
                                                      @RequestBody CreateMedicineRequest medicineRequest){
        return medicineService.CreateMedicine(medicalrecord_id, medicineRequest);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObj> UpdateMedicine(Long medicine_id, UpdateMedicineRequest medicineRequest){
        return medicineService.UpdateMedicine(medicine_id, medicineRequest);
    }

    @PutMapping("/delete")
    ResponseEntity<ResponseObj> DeleteMedicine(Long medicine_id){
        return medicineService.DeleteMedicine(medicine_id);
    }

    @PutMapping("/restore")
    ResponseEntity<ResponseObj> RestoreMedicine(Long medicine_id){
        return medicineService.RestoreMedicine(medicine_id);
    }
}
