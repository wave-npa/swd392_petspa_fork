package org.petspa.petcaresystem.authenuser.controller;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.ResponseAPI;
import org.petspa.petcaresystem.authenuser.model.request.profileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Gender;
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

    @GetMapping("/login")
    public ResponseAPI getUsers(){
        ResponseAPI responseAPI = authenUserService.getUsers();
        return responseAPI;
    }

    @PostMapping("/register")
    public ResponseAPI register(@RequestParam(value = "address") String address,
                                @RequestParam(value = "email") String email,
                                @RequestParam(value = "full_name") String full_name,
                                @RequestParam(value = "gender") Gender gender,
                                @RequestParam(value = "password") String password,
                                @RequestParam(value = "phone") Long phone){
        AuthenUser authenUser = new AuthenUser();
        authenUser.setAddress(address.trim());
        authenUser.setEmail(email.trim());
        authenUser.setFull_name(full_name.trim());
        authenUser.setGender(gender);
        authenUser.setPassword(password.trim());
        authenUser.setPhone(phone);
        ResponseAPI responseAPI = authenUserService.register(authenUser);
        return responseAPI;
    }
}
