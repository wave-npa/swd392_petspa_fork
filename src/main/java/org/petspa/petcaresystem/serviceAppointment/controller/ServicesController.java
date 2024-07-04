package org.petspa.petcaresystem.serviceAppointment.controller;

import java.io.IOException;
import java.util.Collection;

import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.service.ServiceAndComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/petspa/service")
@CrossOrigin
@Tag(name = "Service", description = "Service Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Services.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ServicesController {

   private ServiceAndComboService serviceAndComboService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

   @GetMapping("/getAll")
   @CrossOrigin
   public Collection<Services> getAllServices() {
       return serviceAndComboService.findAllService();
   }

   @GetMapping("/get/{serviceId}")
   @CrossOrigin
   public Services getServiceById(@PathVariable Long serviceId) {
       return serviceAndComboService.findServiceById(serviceId);
   }

   @PostMapping("/save")
   @CrossOrigin
   public Services saveService(@RequestBody Services service) {
       return serviceAndComboService.saveService(service);
   }

   @PostMapping("/update")
   @CrossOrigin
   public Services updateService(@RequestBody Services service) {
       return serviceAndComboService.updateService(service);
   }

   @DeleteMapping("/delete/{serviceId}")
   @CrossOrigin
   public Services deleteServiceById(@PathVariable Long serviceId) {
       return serviceAndComboService.deleteService(serviceId);
   }

   @GetMapping("test")
   @CrossOrigin
   public String test(){
    return "test";
   }
}
