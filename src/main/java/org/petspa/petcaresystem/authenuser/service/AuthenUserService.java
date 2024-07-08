package org.petspa.petcaresystem.authenuser.service;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.response.JwtResponseDTO;
import org.petspa.petcaresystem.authenuser.model.response.ResponseAPI;
import org.petspa.petcaresystem.role.model.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AuthenUserService {

    public JwtResponseDTO login(String email, String password);
    public ResponseAPI register(AuthenUser authenUser);
    public UserDetails loadUserByEmail(String email);
    public List<AuthenUser> getAllUser();
    public ResponseAPI getUserById(Long id);
    public ResponseAPI getUsersByRole(Role role);
//    public ResponseAPI getUsersByCreateDateRange(LocalDateTime start_date, LocalDateTime end_date);



}
