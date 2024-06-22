package org.petspa.petcaresystem.authenuser.service.implement;

import org.petspa.petcaresystem.authenuser.mapper.AuthenUserMapper;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.request.passwordRequest.UpdatePassword;
import org.petspa.petcaresystem.authenuser.model.request.profileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.AccountReponse;
import org.petspa.petcaresystem.authenuser.model.response.AuthenuserResponse;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.petspa.petcaresystem.utils.Validate.validateEmail;
import static org.petspa.petcaresystem.utils.Validate.validatePhoneNumber;

@Service
public class AuthenUserServiceImpl implements AuthenUserService {

    @Autowired
    AuthenUserRepository authenUserRepository;
    private final PasswordEncoder passwordEncoder = null;

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> UpdateProflie(Long id, UpdateProfileRequest profileRequest) {
        try {

            AuthenUser user = authenUserRepository.findById(id).orElse(null);

            if (user.equals(null)) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Profile not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }

            if (!profileRequest.getFull_name().isEmpty()) {
                user.setFull_name(profileRequest.getFull_name());
            }

            user.setGender(profileRequest.getGender());

            if (!profileRequest.getAddress().isEmpty()) {
                user.setAddress(profileRequest.getAddress());
            }

            if (!profileRequest.getPhone().isEmpty() && validatePhoneNumber(profileRequest.getPhone())) {
                user.setPhone(profileRequest.getPhone());
            }

            if (!profileRequest.getEmail().isEmpty() && validateEmail(profileRequest.getEmail())) {
                user.setEmail(profileRequest.getEmail());
            }

            AuthenUser updateauthenUser = authenUserRepository.save(user);

            AuthenuserResponse authenuserResponse = AuthenUserMapper.toAuthenUserResponse(updateauthenUser);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Update Profile Successfully")
                    .data(authenuserResponse)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load Profile")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    public ResponseEntity<ResponseObj> UpdatePassword(Long id, UpdatePassword updatePassword){
        try {
            AuthenUser user = authenUserRepository.findById(id).orElse(null);

            if (user.equals(null)) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("User not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }

            if (!updatePassword.getPassword().equals(user.getPassword()) && updatePassword.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatePassword.getPassword()));
            }

            authenUserRepository.save(user);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Update Profile Successfully")
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load Profile")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }
}