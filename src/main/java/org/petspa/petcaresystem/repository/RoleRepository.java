package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
