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
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @GetMapping("getAll")
    public ResponseEntity<ResponseObj> ViewAllDepartment(){
        return departmentService.ViewAllDepartment();
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseObj> CreateDepartment(@RequestParam(value = "department name") String departmentName,
                                                        @RequestParam(value = "address") String address,
                                                        @RequestParam(value = "status")Status status){
        return departmentService.CreateDepartment(departmentName.trim(), address.trim(), status);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObj> UpdateDepartment(@RequestParam(value = "department id") Long departmentId,
                                                 @RequestParam(value = "department name") String departmentName,
                                                 @RequestParam(value = "address") String address,
                                                 @RequestParam(value = "status")Status status){
        return departmentService.UpdateDepartment(departmentId, departmentName, address, status);
    }

    @GetMapping("/getDeparment")
    ResponseEntity<ResponseObj> getDepartment(@RequestParam Long Department_id){
        return departmentService.getDepartment(Department_id);
    }

}
