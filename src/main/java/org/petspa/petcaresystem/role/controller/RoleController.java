package org.petspa.petcaresystem.role.controller;

import java.io.IOException;
import java.util.Collection;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.role.model.Role;
import org.petspa.petcaresystem.role.repository.RoleRepository;
import org.petspa.petcaresystem.role.service.RoleService;
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
@RequestMapping("/petspa/role")
@CrossOrigin
@Tag(name = "Role", description = "Role Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = Role.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class RoleController {

    @Autowired   
    RoleService roleService;

    @Hidden
    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

   @GetMapping("/getAll")
   @CrossOrigin
   public Collection<Role> getAllRole() {
       return roleService.findAllRole();
   }

   @GetMapping("/get/{roleId}")
   @CrossOrigin
   public Role getRoleById(@PathVariable Long roleId) {
       return roleService.findRoleById(roleId);
   }

   @PostMapping("/save")
   @CrossOrigin
   public Role saveRole(@RequestBody Role role) {
       return roleService.saveRole(role);
   }

   @PostMapping("/update")
   @CrossOrigin
   public Role updateRole(@RequestBody Role role) {
       return roleService.updateRole(role);
   }

   @DeleteMapping("/delete/{roleId}")
   @CrossOrigin
   public Role deleteRoleById(@PathVariable Long roleId) {
       return roleService.deleteRole(roleId);
   }


}
