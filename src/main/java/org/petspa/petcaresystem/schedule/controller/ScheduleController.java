package org.petspa.petcaresystem.schedule.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleRequest;
import org.petspa.petcaresystem.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/petspa/schedule")
@CrossOrigin
@Tag(name = "schedule", description = "schedule Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Schedule.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ScheduleController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = {"/ViewAllSchedule"})
    public ResponseEntity<ResponseObj> ViewAllSchedule(){
        return scheduleService.ViewAllSchedule();
    };

    @GetMapping(value = {"/ViewSchedule/{doctor_id}"})
    public ResponseEntity<ResponseObj> ViewSchedulebyDoctorId(@PathVariable Long doctor_id){
        return scheduleService.ViewSchedulebyDoctorId(doctor_id);
    }
    @PostMapping("/{doctor_id}/save")
    public ResponseEntity<ResponseObj> CreateSchedule(@PathVariable Long doctor_id,
                                                      @RequestBody CreateScheduleRequest createScheduleRequest){
        return scheduleService.CreateSchedule(doctor_id, createScheduleRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObj> UpdateSchedule(@RequestParam Long schedule_id,
                                                      @RequestBody UpdateScheduleRequest updateScheduleRequest ){
        return scheduleService.UpdateSchedule(schedule_id, updateScheduleRequest);
    }

    @PutMapping("/delete")
    public ResponseEntity<ResponseObj> DeleteSchedule(@RequestParam Long schedule_id){
        return scheduleService.DeleteSchedule(schedule_id);
    }

    @PutMapping("/restore")
    public ResponseEntity<ResponseObj> RestoreSchedule(@RequestParam Long schedule_id){
        return scheduleService.RestoreSchedule(schedule_id);
    }
}
