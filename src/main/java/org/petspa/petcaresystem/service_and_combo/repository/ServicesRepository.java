package org.petspa.petcaresystem.service_and_combo.repository;

import org.petspa.petcaresystem.service_and_combo.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {

}
