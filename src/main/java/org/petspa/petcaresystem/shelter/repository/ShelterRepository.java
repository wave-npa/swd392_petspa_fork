package org.petspa.petcaresystem.shelter.repository;

import lombok.Setter;
import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long>{
    public Shelter findFirstByShelterStatus(ShelterStatus shelterStatus);
    public Shelter findByShelterId(Long id);
    public Shelter findByShelterName(String name);
}
