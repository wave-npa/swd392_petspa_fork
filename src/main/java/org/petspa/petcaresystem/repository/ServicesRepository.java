package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Integer> {

}
