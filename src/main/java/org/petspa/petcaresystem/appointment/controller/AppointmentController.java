package org.petspa.petcaresystem.appointment.controller;

import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.service.AppointmentService;
import org.petspa.petcaresystem.enums.Option;
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
   public Collection<Appointment> getAllAppointment() {
       return appointmentService.findAllAppointment();
   }

   @GetMapping("/get/{appointmentId}")
   @CrossOrigin
   public Appointment getAppointmentById(@PathVariable Long appointmentId) {
       return appointmentService.findAppointmentById(appointmentId);
   }

   @PostMapping("/save")
   @CrossOrigin
   public AppointmentResponseDTO createNewAppointment(@RequestBody CreateAppointmentRequestDTO createAppointmentRequestDTO,
                                                      @RequestParam(value = "hospitalize")Option option) {
      AppointmentResponseDTO appointmentResponseDTO = appointmentService.saveAppointment(createAppointmentRequestDTO, option);
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
