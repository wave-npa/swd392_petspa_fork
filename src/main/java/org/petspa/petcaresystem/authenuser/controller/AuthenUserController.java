package org.petspa.petcaresystem.authenuser.controller;

import org.petspa.petcaresystem.authenuser.model.request.ProfileRequest.UpdateProfileRequest;
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

    @PutMapping("/profile/{id}")
    public ResponseEntity<ResponseObj> UpdateProflie(@PathVariable String id,
                                                     @RequestBody UpdateProfileRequest profileRequest){
        return authenUserService.UpdateProflie(id, profileRequest);
    }

}
