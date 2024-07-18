package org.petspa.petcaresystem.schedule.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleDetailRequestDTO;
import org.petspa.petcaresystem.schedule.model.response.ResponseInfor;
import org.petspa.petcaresystem.schedule.service.ScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/petspa/scheduledetail")
@CrossOrigin
@Tag(name = "scheduleDetail", description = "Schedule Detail Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = ScheduleDetail.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
public class ScheduleDetailController {

    @Autowired
    private ScheduleDetailService scheduleDetailService;

    @PostMapping("/save")
    public ResponseInfor createScheduleDetail(@RequestParam(value = "scheduleId") Long scheduleId,
                                              @RequestBody CreateScheduleDetailRequestDTO createScheduleDetailRequestDTO) {
        ResponseInfor responseInfor = scheduleDetailService.createScheduleDetail(scheduleId, createScheduleDetailRequestDTO);
        return responseInfor;
    }

    @PutMapping("/update")
    public ResponseInfor updateScheduleDetail(@RequestParam Long detailId,
                                              @RequestBody CreateScheduleDetailRequestDTO updateScheduleDetailRequest) {
        ResponseInfor responseInfor = scheduleDetailService.updateScheduleDetail(detailId, updateScheduleDetailRequest);
        return responseInfor;
    }
}
