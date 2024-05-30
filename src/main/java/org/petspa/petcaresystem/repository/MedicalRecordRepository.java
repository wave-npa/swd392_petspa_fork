package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer>{
}
