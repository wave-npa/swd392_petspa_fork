package org.petspa.petcaresystem.authenuser.repository;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthenUserRepository extends JpaRepository<AuthenUser, Long>{
    public AuthenUser findByEmail(String email);
    public AuthenUser findByPhone(Long phone);
}
