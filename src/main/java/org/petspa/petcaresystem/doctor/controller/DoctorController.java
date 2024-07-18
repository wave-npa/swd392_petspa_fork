package org.petspa.petcaresystem.doctor.controller;

import java.util.Collection;
import java.util.List;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.model.DoctorData;
import org.petspa.petcaresystem.doctor.model.DoctorResponseDTO;
import org.petspa.petcaresystem.doctor.model.reponse.DoctorResponseData;
import org.petspa.petcaresystem.doctor.service.DoctorService;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/petspa/doctor")
@CrossOrigin
@Tag(name = "Doctor", description = "Doctor Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = AuthenUser.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class DoctorController {

   @Autowired
   private DoctorService doctorService;

   @GetMapping("/getAll")
   @CrossOrigin
   public List<DoctorResponseDTO> getAllDoctor() {
       return doctorService.findAllDoctor();
   }

   @GetMapping("/get/{doctorId}")
   @CrossOrigin
   public AuthenUser getDoctorById(@PathVariable Long doctorId) {
       return doctorService.findDoctorById(doctorId);
   }

   @PostMapping("/save")
   @CrossOrigin
   public Doctor saveDoctor(@RequestBody Doctor doctor) {
       return doctorService.saveDoctor(doctor);
   }

   @PostMapping("/update")
   @CrossOrigin
   public Doctor updateDoctor(@RequestBody Doctor doctor) {
       return doctorService.updateDoctor(doctor);
   }

   @DeleteMapping("/delete/{doctorId}")
   @CrossOrigin
   public Doctor deleteDoctorById(@PathVariable Long doctorId) {
       return doctorService.deleteDoctor(doctorId);
   }

   @GetMapping("/getByUserId/{userId}")
   @CrossOrigin
   public ResponseEntity<DoctorResponseData> getDoctorByUserId(@PathVariable(value = "userId") Long userId){
      ResponseEntity<DoctorResponseData> responseEntity = doctorService.findDoctorByUserId(userId);
      return responseEntity;
   }


}
