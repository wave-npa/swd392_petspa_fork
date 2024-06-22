package org.petspa.petcaresystem.authenuser.controller;

import org.petspa.petcaresystem.authenuser.model.request.passwordRequest.UpdatePassword;
import org.petspa.petcaresystem.authenuser.model.request.profileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile-management")
public class AuthenUserController {
    @Autowired
    AuthenUserService authenUserService;

    @PutMapping(value = {"/profile/{id}"})
    public ResponseEntity<ResponseObj> UpdateProflie(@PathVariable Long id,
                                                     @RequestBody UpdateProfileRequest profileRequest){
        return authenUserService.UpdateProflie(id, profileRequest);
    }




}
