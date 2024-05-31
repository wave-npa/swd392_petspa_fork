package org.petspa.petcaresystem.user.repository;

import org.petspa.petcaresystem.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
