package org.petspa.petcaresystem.serviceAppointment.controller;

import java.io.IOException;
import java.util.Collection;

import org.petspa.petcaresystem.serviceAppointment.model.ServiceType;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.service.ServiceAndComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/petspa/type")
@CrossOrigin
@Tag(name = "Type", description = "Service Type Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = ServiceType.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class TypeController {

    @Autowired
    ServiceAndComboService serviceAndComboService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

     @GetMapping("/getAll")
    @CrossOrigin
    public Collection<ServiceType> getAllServiceType() {
       return serviceAndComboService.findAllServiceType();
    }

    @PostMapping("/save")
    @CrossOrigin
    public ServiceType saveServiceType(@RequestBody ServiceType serviceType) {
       return serviceAndComboService.saveServiceType(serviceType);
    }

}
