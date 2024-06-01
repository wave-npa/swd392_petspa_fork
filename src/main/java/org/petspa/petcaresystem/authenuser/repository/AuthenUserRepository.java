package org.petspa.petcaresystem.authenuser.repository;

import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthenUserRepository extends JpaRepository<AuthenUser, Integer>{
    Optional<AuthenUser> findById(@Param("id") String id);
}
