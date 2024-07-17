package org.petspa.petcaresystem.serviceAppointment.controller;

import java.io.IOException;
import java.util.Collection;

import org.petspa.petcaresystem.serviceAppointment.model.Combo;
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
@RequestMapping("/petspa/combo")
@CrossOrigin
@Tag(name = "Combo", description = "Combo Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Combo.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class ComboController {

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
   public Collection<Combo> getAllCombo() {
       return serviceAndComboService.findAllCombo();
   }

   @GetMapping("/get/{comboId}")
   @CrossOrigin
   public Combo getComboById(@PathVariable Long comboId) {
       return serviceAndComboService.findComboById(comboId);
   }

   @PostMapping("/save")
   @CrossOrigin
   public Combo saveCombo(@RequestBody Combo combo) {
       return serviceAndComboService.saveCombo(combo);
   }

   @PostMapping("/update")
   @CrossOrigin
   public Combo updateCombo(@RequestBody Combo combo) {
       return serviceAndComboService.updateCombo(combo);
   }

   @DeleteMapping("/delete/{comboId}")
   @CrossOrigin
   public Combo deleteServiceById(@PathVariable Long comboId) {
       return serviceAndComboService.deleteCombo(comboId);
   }

}
