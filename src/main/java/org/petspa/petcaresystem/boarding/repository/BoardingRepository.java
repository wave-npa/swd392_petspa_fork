package org.petspa.petcaresystem.boarding.repository;

import org.petspa.petcaresystem.boarding.model.Boarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardingRepository extends JpaRepository<Boarding, String>{

}
