package org.petspa.petcaresystem.shelter.repository;

import org.petspa.petcaresystem.shelter.model.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, String>{

}
