package org.petspa.petcaresystem.authenuser.service.implement;

import org.petspa.petcaresystem.authenuser.mapper.AuthenUserMapper;
import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.request.ProfileRequest.UpdateProfileRequest;
import org.petspa.petcaresystem.authenuser.model.response.AuthenuserResponse;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.authenuser.service.AuthenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.petspa.petcaresystem.utils.Validate.isValidPhoneNumber;

@Service
public class AuthenUserServiceImpl implements AuthenUserService {

    @Autowired
    AuthenUserRepository authenUserRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> UpdateProflie(String id, UpdateProfileRequest profileRequest){
        try {

            Optional<AuthenUser> authenUser = authenUserRepository.findById(id);

            if (!authenUser.isPresent()){
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Profile not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            AuthenUser user = authenUser.get();
            if (!profileRequest.getFull_name().equals(null)){
                user.setFull_name(profileRequest.getFull_name());
            }

            if (!profileRequest.getGender().equals(null)){
                user.setGender(profileRequest.getGender());
            }

            if (!profileRequest.getAddress().equals(null)){
                user.setAddress(profileRequest.getAddress());
            }

            if (!profileRequest.getPhone().equals(null) && isValidPhoneNumber(profileRequest.getPhone())){
                user.setPhone(profileRequest.getPhone());
            }

            AuthenUser updateauthenUser = authenUserRepository.save(user);

            AuthenuserResponse authenuserResponse = AuthenUserMapper.toAuthenUserResponse(updateauthenUser);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Update Profile Successfully")
                    .data(authenuserResponse)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        }catch (Exception e){
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load Profile")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }
}
