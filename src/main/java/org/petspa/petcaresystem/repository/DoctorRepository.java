package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
