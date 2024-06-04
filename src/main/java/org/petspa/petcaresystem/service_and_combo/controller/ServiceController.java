package org.petspa.petcaresystem.service_and_combo.controller;

import java.util.Collection;

import org.petspa.petcaresystem.service_and_combo.model.Services;
import org.petspa.petcaresystem.service_and_combo.service.ServiceAndComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/petspa/service")
@Tag(name = "Service", description = "Service Management API")
public class ServicesandCombosController {

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
        return serviceAndComboService.save(service);
    }

    @PostMapping("/update")
    @CrossOrigin
    public Services updateService(@RequestBody Services service) {
        return serviceAndComboService.update(service);
    }

    @DeleteMapping("/delete/{serviceId}")
    @CrossOrigin
    public Services deleteServiceById(@PathVariable String serviceId) {
        return serviceAndComboService.delete(serviceId);
    }
}
