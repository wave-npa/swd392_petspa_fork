package org.petspa.petcaresystem.boarding.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.boarding.model.request.CreateBoardingRequest;
import org.petspa.petcaresystem.boarding.model.request.UpdateBoardingRequest;
import org.petspa.petcaresystem.boarding.service.BoardingService;
import org.petspa.petcaresystem.department.model.entity.Departments;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//@RestController
//@RequestMapping("api")
//@Controller
@RestController
@RequestMapping("/petspa/boarding")
@CrossOrigin
@Tag(name = "boarding", description = "boarding Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = BoardingAppointment.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class BoardingController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private BoardingService boardingService;

    @GetMapping(value = {"/ViewAllBoarding"})
    public ResponseEntity<ResponseObj> ViewAllBoarding(){
        return boardingService.ViewAllBoarding();
    }

    @GetMapping(value = {"/{appointment_id}/ViewBoardingByAppoinment"})
    public ResponseEntity<ResponseObj> ViewABoardingbyAppointmentId(@PathVariable Long appointment_id){
        return boardingService.ViewABoardingbyAppointmentId(appointment_id);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObj> CreateBoarding(@RequestBody CreateBoardingRequest boardingRequest){
        return boardingService.CreateBoarding(boardingRequest);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObj> UpdateBoarding(@RequestParam Long boarding_id,
                                               @RequestBody UpdateBoardingRequest boardingRequest){
        return boardingService.UpdateBoarding(boarding_id, boardingRequest);
    }

    @PutMapping("/detele")
    ResponseEntity<ResponseObj> DeleteBoarding(@RequestParam Long boarding_id){
        return boardingService.DeleteBoarding(boarding_id);
    }

    @PutMapping("/restore")
    ResponseEntity<ResponseObj> RestoreBoarding(@RequestParam Long boarding_id){
        return boardingService.RestoreBoarding(boarding_id);
    }

}
