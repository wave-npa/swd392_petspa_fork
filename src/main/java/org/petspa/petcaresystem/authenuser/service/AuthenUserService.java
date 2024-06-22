package org.petspa.petcaresystem.authenuser.service;

import org.petspa.petcaresystem.authenuser.model.request.passwordRequest.UpdatePassword;
import org.petspa.petcaresystem.authenuser.model.request.profileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenUserService {

    ResponseEntity<ResponseObj> UpdateProflie(Long id, UpdateProfileRequest profileRequest);

    ResponseEntity<ResponseObj> UpdatePassword(Long id, UpdatePassword updatePassword);
}
