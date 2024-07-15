package org.petspa.petcaresystem.boarding_detail.controller;

import java.io.IOException;
import java.util.Collection;

import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.boarding_detail.service.BoardingDetailService;
import org.petspa.petcaresystem.department.model.entity.Departments;
import org.petspa.petcaresystem.department.model.request.CreateDepartmentRequest;
import org.petspa.petcaresystem.department.model.request.UpdateDepartmentRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@RequestMapping("/petspa/boardingDetail")
@Tag(name = "Boarding Detail", description = "Boarding Detail Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = BoardingDetail.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class BoardingDetailController {

    @Autowired
    BoardingDetailService boardingDetailService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

   @GetMapping("/getAll")
   @CrossOrigin
   public Collection<BoardingDetail> getAllBoardingDetail() {
       return boardingDetailService.findAllBoardingDetail();
   }

   @GetMapping("/get/{boardingDetailId}")
   @CrossOrigin
   public BoardingDetail getBoardingDetailById(@PathVariable Long boardingDetailId) {
       return boardingDetailService.findBoardingDetailById(boardingDetailId);
   }

   @PostMapping("/save")
   @CrossOrigin
   public BoardingDetail saveBoardingDetail(@RequestBody BoardingDetail boardingDetail) {
       return boardingDetailService.saveBoardingDetail(boardingDetail);
   }

   @PostMapping("/update")
   @CrossOrigin
   public BoardingDetail updateService(@RequestBody BoardingDetail boardingDetail) {
       return boardingDetailService.updateBoardingDetail(boardingDetail);
   }

   @DeleteMapping("/delete/{boardingDetailId}")
   @CrossOrigin
   public BoardingDetail deleteBoardingDetailById(@PathVariable Long boardingDetailId) {
       return boardingDetailService.deleteBoardingDetail(boardingDetailId);
   }
}
