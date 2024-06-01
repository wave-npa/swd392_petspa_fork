package org.petspa.petcaresystem.authenuser.controller;

import org.petspa.petcaresystem.authenuser.model.request.ProfileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile-management")
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @PutMapping("/profile/{id}")
    public ResponseEntity<ResponseObj> UpdateProflie(@PathVariable String id,
                                                     @RequestBody UpdateProfileRequest profileRequest){
        return profileService.UpdateProflie(id, profileRequest);
    }

}
