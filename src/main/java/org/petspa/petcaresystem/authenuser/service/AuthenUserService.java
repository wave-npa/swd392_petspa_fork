package org.petspa.petcaresystem.authenuser.service;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.response.*;
import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.role.model.Role;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface AuthenUserService {

    public JwtResponseDTO login(String email, String password);
    public RegisterResponseDTO register(AuthenUser authenUser, String passwordConfirm);
    public List<AuthenUser> getAllUser();
    public ResponseAPI getUserById(Long id);
    public UpdateProfileResponseDTO updateProfile(AuthenUser authenUser);
    public UpdatePassowordResponseDTO updatePassword(String current_password, String new_password, String confirm_password);
    public UpdatePassowordResponseDTO logout();
    public ResponseAPI getUsersByRole(Role role);
    public ResponseAPI getUsersByCreateDateRange(LocalDateTime start_date, LocalDateTime end_date);
    public ResponseAPI searchByUserNameTEST(String searchTerm, Gender gender, Status status, String orderBy, String order);
    public ResponseAPI findAllUsersWithAgeRange(Integer startAge, Integer endAge);
    public ResponseAPI updateUserRole(Long userId, Role role);
    public ResponseAPI deleteUser(Long userId);
    public AuthenUser createUser(AuthenUser authenUser);

    public InforResponseDTO verifyRegister(String userEnterCode);
    public AuthenUser getCurrentUser(String token);
    public InforResponseDTO forgetPassword(String email);
}
