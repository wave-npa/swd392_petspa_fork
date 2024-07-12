package org.petspa.petcaresystem.appointment.controller;

import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.request.UpdateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseInfor;
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

   @GetMapping("/getAppointment/{appointmentId}")
   @CrossOrigin
   public AppointmentResponseDTO getAppointmentById(@PathVariable Long appointmentId) {
      AppointmentResponseDTO appointmentResponseDTO = appointmentService.findAppointmentById(appointmentId);
       return appointmentResponseDTO;
   }

   @PostMapping("/save")
   @CrossOrigin
   public AppointmentResponseInfor createNewAppointment(@RequestBody CreateAppointmentRequestDTO createAppointmentRequestDTO,
                                                        @RequestParam(value = "hospitalize")Option option) {
      AppointmentResponseInfor appointmentResponseInfor = appointmentService.saveAppointment(createAppointmentRequestDTO, option);
       return appointmentResponseInfor;
   }

   @PutMapping("/update")
   @CrossOrigin
   public AppointmentResponseInfor updateAppointment(@RequestBody UpdateAppointmentRequestDTO updateAppointmentRequestDTO) {
      AppointmentResponseInfor appointmentResponseInfor = appointmentService.updateAppointment(updateAppointmentRequestDTO);
       return appointmentResponseInfor;
   }

   @DeleteMapping("/delete/{appointmentId}")
   @CrossOrigin
   public Appointment deleteAppointmentById(@PathVariable Long appointmentId) {
       return appointmentService.deleteAppointment(appointmentId);
   }

}
