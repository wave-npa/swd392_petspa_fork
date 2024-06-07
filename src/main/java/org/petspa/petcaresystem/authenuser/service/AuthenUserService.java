package org.petspa.petcaresystem.authenuser.service;

import org.petspa.petcaresystem.authenuser.model.request.ProfileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthenUserService {

    ResponseEntity<ResponseObj> UpdateProflie(int id, UpdateProfileRequest profileRequest);

}
