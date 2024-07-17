package org.petspa.petcaresystem.doctor.repository;

import org.petspa.petcaresystem.doctor.model.DoctorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDataRepository extends JpaRepository<DoctorData, Long>{

}
