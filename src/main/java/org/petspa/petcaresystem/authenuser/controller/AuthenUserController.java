package org.petspa.petcaresystem.authenuser.controller;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.ResponseAPI;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class AuthenUserController {

    @Autowired
    AuthenUserService authenUserService;

    @GetMapping("/getUsers")
    public List<AuthenUser> getUsers(){
        List<AuthenUser> authenUser = new ArrayList<>();
        authenUser = authenUserService.getUsers();

        return authenUser;
    }

    @PostMapping("/register")
    public ResponseAPI register(@RequestBody AuthenUser authenUser){
        return authenUserService.register(authenUser);
    }
}
