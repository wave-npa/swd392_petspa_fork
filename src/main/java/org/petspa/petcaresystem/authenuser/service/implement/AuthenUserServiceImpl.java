package org.petspa.petcaresystem.authenuser.service.implement;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.ResponseAPI;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.role.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenUserServiceImpl implements AuthenUserService {

    private static final String format_pattern = "yyyy-MM-dd HH:mm";
    private static final String logging_message = "An error occurred:";
    private static final Logger logger = LoggerFactory.getLogger(AuthenUserService.class);

    @Autowired
    AuthenUserRepository authenUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public ResponseAPI getUsers() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Get all users success";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;
        List<AuthenUser> authenUserList = new ArrayList<>();
        try {
            authenUserList = authenUserRepository.findAll();
        }catch (Exception e){
            logger.error(this.logging_message, e);
            message = "Something went wrong, server error!";
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
    }

    @Override
    public ResponseAPI register(AuthenUser authenUser) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Create new account success";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        // encode password
        String encodedPassword = passwordEncoder.encode(authenUser.getPassword());
        authenUser.setPassword(encodedPassword);

        // set status value
        authenUser.setStatus(Status.valueOf("ACTIVE"));

        // set roleID = customer role
        Role role = new Role();
        role.setRoleId(3L);
        authenUser.setRole(role);

        List<AuthenUser> authenUserList = new ArrayList<>();
        authenUserList.add(authenUser);

        // check email exist?
        if(authenUserRepository.findByEmail(authenUser.getEmail()) != null){
            message = "This email has already existed!Please try another";
            return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
        }

        // check phone number exist?
        if(authenUserRepository.findByPhone(authenUser.getPhone()) != null){
            message = "This phone number has already existed!Please try another";
            return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
        }
        
        try{
            authenUserRepository.save(authenUser);
        }catch (Exception e){
            logger.error(this.logging_message, e);
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
    }
}
