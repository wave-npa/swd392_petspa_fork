package org.petspa.petcaresystem.main.repository;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.springframework.stereotype.Repository;

@Repository
public interface MainRepository {
    public AuthenUser findByEmail(String email);
    public AuthenUser findByPhone(Long phone);
}
