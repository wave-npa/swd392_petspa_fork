package org.petspa.petcaresystem.authenuser.repository;

import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.data.repository.query.Param;

import java.util.Optional;
=======
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenUserRepository extends JpaRepository<AuthenUser, String>{
>>>>>>> 9d2f3f6e7400c6e1b7ced54009e8623c2153260e

public interface AuthenUserRepository extends JpaRepository<AuthenUser, String>{
//    Optional<AuthenUser> findById(@Param("id") String id);
}
