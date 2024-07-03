package org.petspa.petcaresystem.authenuser.service;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.ResponseAPI;
import org.petspa.petcaresystem.authenuser.model.request.profileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthenUserService {

    public ResponseAPI getUsers();
    public ResponseAPI register(AuthenUser authenUser);

    public UserDetails loadUserByEmail(String email) throws Exception;
    public ResponseEntity<ResponseObj> UpdateProflie(Long id, UpdateProfileRequest profileRequest);
}
