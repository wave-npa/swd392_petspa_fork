package org.petspa.petcaresystem.appointment.controller;

import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.service.AppointmentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

   AppointmentService appointmentService;

   @GetMapping("/getAll")
   @CrossOrigin
   public Collection<Appointment> getAllAppointment() {
       return appointmentService.findAllAppointment();
   }

   @GetMapping("/get/{appointmentId}")
   @CrossOrigin
   public Appointment getAppointmentById(@PathVariable Long appointmentId) {
       return appointmentService.findAppointmentById(appointmentId);
   }

   @PostMapping("/save")
//   @CrossOrigin
   public AppointmentResponseDTO createNewAppointment(@RequestBody Appointment appointment) {
      AppointmentResponseDTO appointmentResponseDTO = appointmentService.saveAppointment(appointment);
       return appointmentResponseDTO;
   }

   @PostMapping("/update")
   @CrossOrigin
   public Appointment updateAppointment(@RequestBody Appointment appointment) {
       return appointmentService.updateAppointment(appointment);
   }

   @DeleteMapping("/delete/{appointmentId}")
   @CrossOrigin
   public Appointment deleteAppointmentById(@PathVariable Long appointmentId) {
       return appointmentService.deleteAppointment(appointmentId);
   }

}
