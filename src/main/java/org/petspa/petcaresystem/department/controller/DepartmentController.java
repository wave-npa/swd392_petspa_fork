package org.petspa.petcaresystem.department.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.petspa.petcaresystem.department.model.entity.Departments;
import org.petspa.petcaresystem.department.model.request.CreateDepartmentRequest;
import org.petspa.petcaresystem.department.model.request.UpdateDepartmentRequest;
import org.petspa.petcaresystem.department.service.DepartmentService;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/petspa/department")
@CrossOrigin
@Tag(name = "department", description = "department Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Departments.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

   @GetMapping("/getAll")
   @CrossOrigin
   public Collection<Services> getAllServices() {
       return departmentService.findAllService();
   }

   @GetMapping("/get/{serviceId}")
   @CrossOrigin
   public Services getServiceById(@PathVariable Long serviceId) {
       return departmentService.findServiceById(serviceId);
   }

   @PostMapping("/save")
   @CrossOrigin
   public Services saveService(@RequestBody Services service) {
       return departmentService.saveService(service);
   }

   @PostMapping("/update")
   @CrossOrigin
   public Services updateService(@RequestBody Services service) {
       return departmentService.updateService(service);
   }

   @DeleteMapping("/delete/{serviceId}")
   @CrossOrigin
   public Services deleteServiceById(@PathVariable Long serviceId) {
       return departmentService.deleteService(serviceId);
   }
}
