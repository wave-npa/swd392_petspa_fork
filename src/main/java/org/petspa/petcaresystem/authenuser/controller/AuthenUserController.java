package org.petspa.petcaresystem.authenuser.controller;

import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.request.ProfileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/petspa/user")
@CrossOrigin
@Tag(name = "User", description = "User Management API")
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(implementation = AuthenUser.class), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class AuthenUserController {
    @Autowired
    AuthenUserService authenUserService;

    @GetMapping("/getAll")
    @CrossOrigin
    public String test() {
        return "test";
    }

    @PutMapping("/profile/{id}")
    @CrossOrigin
    public ResponseEntity<ResponseObj> UpdateProflie(@PathVariable Long id,
                                                     @RequestBody UpdateProfileRequest profileRequest){
        return authenUserService.UpdateProflie(id, profileRequest);
    }



}
