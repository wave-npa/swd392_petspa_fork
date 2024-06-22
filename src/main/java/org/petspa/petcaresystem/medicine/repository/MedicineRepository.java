package org.petspa.petcaresystem.medicine.repository;

import org.petspa.petcaresystem.medicine.model.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String>{

}
