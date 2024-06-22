package org.petspa.petcaresystem.authenuser.controller;


import org.petspa.petcaresystem.authenuser.model.request.passwordRequest.UpdatePassword;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Account-management")
public class AccountController {
    @Autowired
    AuthenUserService authenUserService;

    @PutMapping("/Account/{id}")
    public ResponseEntity<ResponseObj> UpdatePassword(@PathVariable Long id,
                                                      @RequestBody UpdatePassword updatePassword) {
        return authenUserService.UpdatePassword(id, updatePassword);
    }
}