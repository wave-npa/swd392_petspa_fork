package org.petspa.petcaresystem.appointment.controller;

import java.net.http.HttpResponse;
import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.request.UpdateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO2;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseInfor;
import org.petspa.petcaresystem.appointment.service.AppointmentService;
import org.petspa.petcaresystem.enums.Option;
import org.petspa.petcaresystem.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/petspa/appointment")
@CrossOrigin
@Tag(name = "Appointment", description = "Appointment Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Appointment.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class AppointmentController {

   @Autowired
   AppointmentService appointmentService;

   @GetMapping("/getAll")
   @CrossOrigin
   public AppointmentResponseDTO2 getAllAppointment() {
      AppointmentResponseDTO2 appointmentResponseDTO2 = appointmentService.findAllAppointment();
       return appointmentResponseDTO2;
   }

   @GetMapping("/getById/{appointmentId}")
   @CrossOrigin
   public AppointmentResponseDTO2 getAppointmentById(@PathVariable Long appointmentId) {
      AppointmentResponseDTO2 appointmentResponseDTO2 = appointmentService.findAppointmentById(appointmentId);
       return appointmentResponseDTO2;
   }

   @PostMapping("/save")
   @CrossOrigin
   public AppointmentResponseInfor createNewAppointment(@RequestBody CreateAppointmentRequestDTO createAppointmentRequestDTO,
                                                        @RequestParam(value = "hospitalize")Option option,

                                                        @RequestParam(value = "full name") String fullName,
                                                        @RequestParam(value = "phone") String phone,
                                                        @RequestParam(value = "email") String email) {
      AppointmentResponseInfor appointmentResponseInfor = appointmentService.saveAppointment(createAppointmentRequestDTO, option, fullName, phone, email);
       return appointmentResponseInfor;
   }

   @PutMapping("/update")
   @CrossOrigin
   public AppointmentResponseInfor updateAppointment(@RequestBody UpdateAppointmentRequestDTO updateAppointmentRequestDTO) {
      AppointmentResponseInfor appointmentResponseInfor = appointmentService.updateAppointment(updateAppointmentRequestDTO);
       return appointmentResponseInfor;
   }

   @PutMapping("/updateStatus")
   @CrossOrigin
   public AppointmentResponseInfor updateAppointmentStatus(@RequestParam Long appointmentId, @RequestParam Status status) {
      AppointmentResponseInfor appointmentResponseInfor = appointmentService.updateAppointmentStatus(appointmentId, status);
       return appointmentResponseInfor;
   }

   @GetMapping("/getByUserId")
   private AppointmentResponseDTO2 getAppointmentByUserId(@RequestParam(value = "userId") Long userId){
      AppointmentResponseDTO2 appointmentResponseDTO2 = appointmentService.findAllAppointmentByUserId(userId);
      return appointmentResponseDTO2;
   }

   @GetMapping("/getByDoctorId")
   private AppointmentResponseDTO2 getAppointmentByDoctorId(@RequestParam(value = "doctorId") Long doctorId){
      AppointmentResponseDTO2 appointmentResponseDTO2 = appointmentService.findAppointmentByDoctorId(doctorId);
      return appointmentResponseDTO2;
   }

   @DeleteMapping("delete/{appointmentId}")
   private AppointmentResponseInfor deleteAppointmentById(@PathVariable(value = "appointmentId") Long appointmentId){
      AppointmentResponseInfor appointmentResponseInfor = appointmentService.deleteAppointment(appointmentId);
      return appointmentResponseInfor;

   }
}
