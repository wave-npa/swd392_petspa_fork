package org.petspa.petcaresystem.doctor.controller;

import java.util.Collection;

import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DoctorController {

   private DoctorService doctorService;

   @GetMapping("/getAll")
   @CrossOrigin
   public Collection<Doctor> getAllDoctor() {
       return doctorService.findAllDoctor();
   }

   @GetMapping("/get/{doctorId}")
   @CrossOrigin
   public Doctor getDoctorById(@PathVariable Long doctorId) {
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

}
