package org.petspa.petcaresystem.user.repository;

import org.petspa.petcaresystem.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

}
