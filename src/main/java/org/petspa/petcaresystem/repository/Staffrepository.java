package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface Staffrepository extends JpaRepository<Staff, Integer> {
}
