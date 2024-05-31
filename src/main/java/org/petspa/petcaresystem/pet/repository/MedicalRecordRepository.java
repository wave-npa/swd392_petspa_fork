package org.petspa.petcaresystem.pet.repository;

import org.petspa.petcaresystem.pet.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String>{
}
