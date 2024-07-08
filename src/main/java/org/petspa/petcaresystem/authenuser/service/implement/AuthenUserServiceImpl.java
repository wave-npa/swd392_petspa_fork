package org.petspa.petcaresystem.authenuser.service.implement;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.session.StandardSession;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.response.JwtResponseDTO;
import org.petspa.petcaresystem.authenuser.model.response.ResponseAPI;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.petspa.petcaresystem.config.JwtUtil;
import org.petspa.petcaresystem.config.MyUserDetails;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.role.model.Role;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenUserServiceImpl implements AuthenUserService {

    private static final String format_pattern = "yyyy-MM-dd HH:mm";
    private static final String logging_message = "An error occurred:";
    private static final Logger logger = LoggerFactory.getLogger(AuthenUserService.class);

    @Autowired
    AuthenUserRepository authenUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    private HttpServletRequest request;

    @Override
    public JwtResponseDTO login(String email, String password) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Login success";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;
        Optional<AuthenUser> authenUser;
        String jwtToken = "";

        try {
            authenUser = Optional.ofNullable(authenUserRepository.findByEmailAndPassword(email, password));
            if(authenUser != null){
                jwtToken = jwtUtil.generateToken(authenUser.get().getEmail(), authenUser.get().getRole().getRoleName(), authenUser.get().getUserName());
                HttpSession session = request.getSession();
                session.setAttribute("jwtToken", jwtToken);
            }
        } catch (AuthenticationException e) {
            message = "Invalid email/password";
            statusValue = HttpStatus.UNAUTHORIZED;
        } catch (Exception e) {
            logger.error("Error occurred during login", e);
            message = "Something went wrong, server error!";
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new JwtResponseDTO(jwtToken, message, timeStamp, statusCode, statusValue);
    }


    @Override
    public ResponseAPI register(AuthenUser authenUser) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Create new account successfully";
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
        if (authenUserRepository.findByEmail(authenUser.getEmail()) != null) {
            message = "This email has already existed!Please try another";
            return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
        }

        // check phone number exist?
        if (authenUserRepository.findByPhone(authenUser.getPhone()) != null) {
            message = "This phone number has already existed!Please try another";
            return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
        }

        try {
            authenUserRepository.save(authenUser);
        } catch (Exception e) {
            logger.error(this.logging_message, e);
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
    }

    @Override
    public UserDetails loadUserByEmail(String email) {
        AuthenUser authenUser = authenUserRepository.findByEmail(email);
        return new MyUserDetails(authenUser);
    }


    @Override
    public List<AuthenUser> getAllUser(){
        List<AuthenUser> authenUserList = authenUserRepository.findAll();
        return authenUserList;
    }

    @Override
    public ResponseAPI getUserById(Long id) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Get user successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;
        Optional<AuthenUser> authenUser = Optional.ofNullable(new AuthenUser());
        try {
            authenUser = authenUserRepository.findById(id);
        }catch (Exception e){
            logger.error(this.logging_message, e);
            message = "Something went wrong, server error!";
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUser);
    }

    @Override
    public ResponseAPI getUsersByRole(Role role) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Get user successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;
        List<AuthenUser> authenUserList = new ArrayList<>();
        try {
//            authenUserList = authenUserRepository.findByRole(role); // code cua An,
            authenUserList = (List<AuthenUser>) authenUserRepository.findByRole(role);
        }catch (Exception e){
            logger.error(this.logging_message, e);
            message = "Something went wrong, server error!";
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
    }

//    @Override
//    public ResponseAPI getUsersByCreateDateRange(LocalDateTime start_date, LocalDateTime end_date) {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
//        String timeStamp = localDateTime.format(formatter);
//        String message = "Get user successfully";
//        int statusCode = HttpStatus.OK.value();
//        HttpStatus statusValue = HttpStatus.OK;
//        List<AuthenUser> authenUserList = new ArrayList<>();
//        try {
//            authenUserList = authenUserRepository.findAllUsersWithCreateDateRange(start_date, end_date);
//        }catch (Exception e){
//            logger.error(this.logging_message, e);
//            message = "Something went wrong, server error!";
//            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseAPI(timeStamp, message, statusCode, statusValue, authenUserList);
//    }
}
