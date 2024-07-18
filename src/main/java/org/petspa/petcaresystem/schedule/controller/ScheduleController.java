package org.petspa.petcaresystem.schedule.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequestDTO;
import org.petspa.petcaresystem.schedule.model.response.ResponseInfor;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponseData;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponseDTO;
import org.petspa.petcaresystem.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/petspa/schedule")
@CrossOrigin
@Tag(name = "schedule", description = "schedule Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Schedule.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping(value = {"/viewAllSchedule"})
    public ScheduleResponseDTO ivewAllSchedule(){
        ScheduleResponseDTO scheduleResponseDTO = scheduleService.viewAllSchedule();
        return scheduleResponseDTO;
    }

    @GetMapping(value = {"/viewSchedule/{doctorId}"})
    public ScheduleResponseDTO ViewSchedulebyDoctorId(@PathVariable(value = "doctorId") Long doctorId){
        ScheduleResponseDTO scheduleResponseDTO = scheduleService.viewSchedulebyDoctorId(doctorId);
        return scheduleResponseDTO;
    }
    @PostMapping("/save")
    public ResponseInfor createSchedule(@RequestBody CreateScheduleRequestDTO createScheduleRequestDTO){
        ResponseInfor responseInfor = scheduleService.createSchedule(createScheduleRequestDTO);
        return responseInfor;
    }

    @PutMapping("/update")
    public ResponseInfor UpdateSchedule(@RequestParam(value = "scheduleId") Long scheduleId,
                                        @RequestBody CreateScheduleRequestDTO createScheduleRequestDTO){
        ResponseInfor responseInfor = scheduleService.updateSchedule(scheduleId, createScheduleRequestDTO);
        return responseInfor;
    }
}
