package org.petspa.petcaresystem.authenuser.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.response.*;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.config.JwtUtil;
import org.petspa.petcaresystem.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;
import java.util.List;

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
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public JwtResponseDTO login(@RequestParam(value = "email") String email,
                                @RequestParam(value = "password") String password){
        JwtResponseDTO jwtResponseDTO = authenUserService.login(email, password);
        return jwtResponseDTO;
    }

    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestParam(value = "user_name") String userName,
                                        @RequestParam(value = "address") String address,
                                        @RequestParam(value = "email") String email,
                                        @RequestParam(value = "full_name") String fullName,
                                        @RequestParam(value = "gender") Gender gender,
                                        @RequestParam(value = "password") String password,
                                        @RequestParam(value = "phone") String phone){
        AuthenUser authenUser = new AuthenUser();
        authenUser.setUserName(userName);
        authenUser.setAddress(address.trim());
        authenUser.setEmail(email.trim());
        authenUser.setFullName(fullName.trim());
        authenUser.setGender(gender);
        authenUser.setPassword(password.trim());
        authenUser.setPhone(phone);
        RegisterResponseDTO registerResponseDTO = authenUserService.register(authenUser);
        return registerResponseDTO;
    }

    @PutMapping("/updateProfile")
    public UpdateProfileResponseDTO updateProfile(@RequestParam(value = "user_name") String userName,
                                        @RequestParam(value = "address") String address,
                                        @RequestParam(value = "email") String email,
                                        @RequestParam(value = "full_name") String fullName,
                                        @RequestParam(value = "gender") Gender gender,
                                        @RequestParam(value = "phone") String phone)
    {
        AuthenUser authenUser = new AuthenUser();
        authenUser.setUserName(userName);
        authenUser.setAddress(address.trim());
        authenUser.setEmail(email.trim());
        authenUser.setFullName(fullName.trim());
        authenUser.setGender(gender);
        authenUser.setPhone(phone);
        UpdateProfileResponseDTO updateProfileResponseDTO = authenUserService.updateProfile(authenUser);
        return updateProfileResponseDTO;
    }

    @PutMapping("/updatePassword")
    public UpdatePassowordResponseDTO updatePassoword(@RequestParam(value = "current_password") String current_password,
                                                      @RequestParam(value = "new_password") String new_password,
                                                      @RequestParam(value = "confirm_password") String confirm_password){
        UpdatePassowordResponseDTO updatePassowordResponseDTO =
                authenUserService.updatePassword(current_password.trim(), new_password.trim(), confirm_password.trim());
        return updatePassowordResponseDTO;
    }

    @GetMapping("/getAllUser")
    public List<AuthenUser> getAllAccount(){
        return authenUserService.getAllUser();
    }

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String jwtToken = (String) session.getAttribute("jwtToken");

        if (jwtToken != null) {
            if (!jwtToken.isEmpty()) {
                return "JWT Token: " + jwtToken;
            } else {
                return "JWT Token do not exist or it empty";
            }
        } else {
            return "Session don't have JWT Token";
        }
    }
}
