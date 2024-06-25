package org.petspa.petcaresystem.authenuser.service.implement;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.ResponseAPI;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.role.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public List<AuthenUser> getUsers() {
        List<AuthenUser> authenUsers = new ArrayList<>();
        authenUsers = authenUserRepository.findAll();
        return authenUsers;
    }

    @Override
    public ResponseAPI register(AuthenUser authenUser) {
        ResponseAPI responseAPI = new ResponseAPI();
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Create new account success";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;
        List<AuthenUser> authenUserList = new ArrayList<>();

        Role role = new Role();

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
