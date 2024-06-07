package org.petspa.petcaresystem.staff.repository;

import org.petspa.petcaresystem.staff.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Staffrepository extends JpaRepository<Staff, String> {
}
