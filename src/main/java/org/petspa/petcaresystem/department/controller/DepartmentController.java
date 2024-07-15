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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//@RestController
//@RequestMapping("api")
//@Controller
@RestController
@RequestMapping("/petspa/department")
@CrossOrigin
@Tag(name = "department", description = "department Management API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Departments.class), mediaType = "application/json") }),
        @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
        @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class DepartmentController {

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }

    @Autowired
    private DepartmentService departmentService;

    @GetMapping(value = {"/viewAllDepartment"})
    public ResponseEntity<ResponseObj> ViewAllDepartment(){
        return departmentService.ViewAllDepartment();
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObj> CreateDepartment(@RequestBody CreateDepartmentRequest departmentRequest){
        return departmentService.CreateDepartment(departmentRequest);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseObj> UpdateDepartment(@RequestParam Long Department_id,
                                                 @RequestBody UpdateDepartmentRequest departmentRequest){
        return departmentService.UpdateDepartment(Department_id, departmentRequest);
    }

    @PutMapping("/detele")
    ResponseEntity<ResponseObj> DeleteDepartment(@RequestParam Long Department_id){
        return departmentService.DeleteDepartment(Department_id);
    }

    @PutMapping("/restore")
    ResponseEntity<ResponseObj> RestoreDepartment(@RequestParam Long Department_id){
        return departmentService.RestoreDepartment(Department_id);
    }
}
