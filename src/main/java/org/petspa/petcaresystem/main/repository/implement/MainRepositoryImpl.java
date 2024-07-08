package org.petspa.petcaresystem.main.repository.implement;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.main.repository.MainRepository;

public class MainRepositoryImpl implements MainRepository {

    @Override
    public AuthenUser findByEmail(String email) {
        return null;
    }

    @Override
    public AuthenUser findByPhone(Long phone) {
        return null;
    }
}
