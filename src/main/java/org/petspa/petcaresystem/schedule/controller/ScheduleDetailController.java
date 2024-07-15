package org.petspa.petcaresystem.schedule.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleDetailRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleDetailRequest;
import org.petspa.petcaresystem.schedule.service.ScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/petspa/scheduledetail")
@CrossOrigin
@Tag(name = "scheduledetail", description = "scheduledetail Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ScheduleDetail.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ScheduleDetailController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private ScheduleDetailService scheduleDetailService;

    @GetMapping(value = {"/ViewScheduledetailByid/{schedule_id}"})
    public ResponseEntity<ResponseObj> ViewAllScheduleDetailByScheduleId(@PathVariable Long schedule_id){
        return scheduleDetailService.ViewAllScheduleDetailByScheduleId(schedule_id);
    }
    @PostMapping("/{schedule_id}/save")
    public ResponseEntity<ResponseObj> CreateScheduleDetail(@PathVariable Long schedule_id,
                                                            @RequestBody CreateScheduleDetailRequest createScheduleDetailRequest){
        return scheduleDetailService.CreateScheduleDetail(schedule_id, createScheduleDetailRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObj> UpdateScheduleDetail(@RequestParam Long detail_id,
                                                            @RequestBody UpdateScheduleDetailRequest updateScheduleDetailRequest ){
        return scheduleDetailService.UpdateScheduleDetail(detail_id, updateScheduleDetailRequest);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObj> DeleteScheduleDetail(@RequestParam Long detail_id){
        return scheduleDetailService.DeleteScheduleDetail(detail_id);
    }
}
