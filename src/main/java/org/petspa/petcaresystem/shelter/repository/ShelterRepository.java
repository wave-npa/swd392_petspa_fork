package org.petspa.petcaresystem.shelter.repository;

import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long>{
    public Shelter findByShelterStatus(ShelterStatus shelterStatus);

}
