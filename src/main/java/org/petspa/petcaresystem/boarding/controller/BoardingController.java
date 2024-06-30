package org.petspa.petcaresystem.boarding.controller;

import org.petspa.petcaresystem.boarding.model.request.CreateBoardingRequest;
import org.petspa.petcaresystem.boarding.model.request.UpdateBoardingRequest;
import org.petspa.petcaresystem.boarding.service.BoardingService;
import org.petspa.petcaresystem.department.model.request.CreateDepartmentRequest;
import org.petspa.petcaresystem.department.model.request.UpdateDepartmentRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Controller
public class BoardingController {

    @Autowired
    BoardingService boardingService;

    @GetMapping(value = {"/boarding/ViewAllBoarding"})
    public ResponseEntity<ResponseObj> ViewAllBoarding(){
        return boardingService.ViewAllBoarding();
    }

    @GetMapping(value = {"/boarding/{appointment_id}/ViewBoardingByAppoinmentId"})
    public ResponseEntity<ResponseObj> ViewABoardingbyAppointmentId(@PathVariable Long appointment_id){
        return boardingService.ViewABoardingbyAppointmentId(appointment_id);
    }

    @PostMapping("/boarding/create")
    public ResponseEntity<ResponseObj> CreateBoarding(@RequestBody CreateBoardingRequest boardingRequest){
        return boardingService.CreateBoarding(boardingRequest);
    }

    @PutMapping("/boarding/update")
    ResponseEntity<ResponseObj> UpdateBoarding(@RequestParam Long boarding_id,
                                               @RequestBody UpdateBoardingRequest boardingRequest){
        return boardingService.UpdateBoarding(boarding_id, boardingRequest);
    }

    @PutMapping("/boarding/detele")
    ResponseEntity<ResponseObj> DeleteBoarding(@RequestParam Long boarding_id){
        return boardingService.DeleteBoarding(boarding_id);
    }

    @PutMapping("/boarding/restore")
    ResponseEntity<ResponseObj> RestoreBoarding(@RequestParam Long boarding_id){
        return boardingService.RestoreBoarding(boarding_id);
    }

}
