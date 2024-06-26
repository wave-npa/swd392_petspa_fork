package org.petspa.petcaresystem.authenuser.controller;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.ResponseAPI;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.enums.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AuthenUserController {

    @Autowired
    AuthenUserService authenUserService;

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
