package org.petspa.petcaresystem.serviceAppointment.controller;

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

@RestController
@RequestMapping("/petspa/service")
//@Tag(name = "Service", description = "Service Management API")
public class ServicesController {

   @Autowired
   private ServiceAndComboService serviceAndComboService;

   @GetMapping("/getAll")
   @CrossOrigin
   public Collection<Services> getAllServices() {
       return serviceAndComboService.findAllService();
   }

   @GetMapping("/get/{serviceId}")
   @CrossOrigin
   public Services getServiceById(@PathVariable String serviceId) {
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
   public Services deleteServiceById(@PathVariable String serviceId) {
       return serviceAndComboService.deleteService(serviceId);
   }
}
