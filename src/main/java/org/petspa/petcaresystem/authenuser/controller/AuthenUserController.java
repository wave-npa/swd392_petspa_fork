package org.petspa.petcaresystem.authenuser.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.response.*;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/petspa/user")
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

    @PostMapping("/save")
    public AuthenUser saveUser(@RequestBody AuthenUser user) {
       return authenUserService.createUser(user);
    }

    @GetMapping("/login")
    @CrossOrigin
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
                                        @RequestParam(value = "confirm password") String confirmPassword,
                                        @RequestParam(value = "phone") String phone,
                                        @RequestParam(value = "age") int age){
        AuthenUser authenUser = new AuthenUser();
        authenUser.setUserName(userName);
        authenUser.setAddress(address.trim());
        authenUser.setEmail(email.trim());
        authenUser.setFullName(fullName.trim());
        authenUser.setGender(gender);
        authenUser.setPassword(password.trim());
        authenUser.setPhone(phone);
        authenUser.setAge(age);
        RegisterResponseDTO registerResponseDTO = authenUserService.register(authenUser, confirmPassword);
        return registerResponseDTO;
    }

    @GetMapping("/currentUser/{token}")
    public AuthenUser getCurrentUser(@PathVariable String token) {
        return authenUserService.getCurrentUser(token);
    }

    @PutMapping("/updateProfile")
    public UpdateProfileResponseDTO updateProfile(@RequestParam(value = "userId") Long userId,
                                        @RequestParam(value = "user_name") String user_name,
                                        @RequestParam(value = "address") String address,
                                        @RequestParam(value = "email") String email,
                                        @RequestParam(value = "full_name") String fullName,
                                        @RequestParam(value = "gender") Gender gender,
                                        @RequestParam(value = "phone") String phone)
    {
        AuthenUser authenUser = authenUserService.getUserById(userId).getData().orElse(null);
        authenUser.setFullName(user_name.trim());
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

    @GetMapping("/searchUser")
    public ResponseAPI searchUser(@RequestParam(value = "searchTerm", defaultValue = "", required = false) String searchTerm,
                                  @RequestParam(value = "gender", required = false) Gender gender,
                                  @RequestParam(value = "status", required = false) Status status,
                                  @RequestParam(value = "orderBy", defaultValue = "user_id") String orderBy,
                                  @RequestParam(value = "order", defaultValue = "ASC") String order)
                                {
        ResponseAPI userResponseAPI = authenUserService.searchByUserNameTEST(searchTerm.trim(), gender, status, orderBy.trim(), order.trim().toUpperCase());
        return userResponseAPI;
    }

    @GetMapping("/findUserByAge")
    public ResponseAPI findhUserByAge(@RequestParam(value = "startAge") Integer startAge,
                                      @RequestParam(value = "endAge") Integer endAge) {
        ResponseAPI userResponseAPI = authenUserService.findAllUsersWithAgeRange(startAge, endAge);
        return userResponseAPI;
    }    
    
    @PostMapping("/logout")
    public UpdatePassowordResponseDTO logout(){
        UpdatePassowordResponseDTO logoutResponse = authenUserService.logout();
        return logoutResponse;
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseAPI getUserById(@PathVariable Long userId){
        ResponseAPI responseAPI = authenUserService.getUserById(userId);
        return responseAPI;
    }

    @GetMapping("/getAllUser")
    public List<AuthenUser> getAllAccount(){
        return authenUserService.getAllUser();
    }

    @GetMapping("/verify")
    public InforResponseDTO veritfyEmail(@RequestParam(value = "verify code") String verifyCode){
        InforResponseDTO inforResponseDTO = authenUserService.verifyRegister(verifyCode.trim());
        return inforResponseDTO;
    }
    //    @PostMapping("/forgetPassword")
//    public InforResponseDTO forgetPassword(@RequestParam(value = "email") String email){
//        InforResponseDTO inforResponseDTO = authenUserService.forgetPassword(email);
//        return inforResponseDTO;
//    }
}
