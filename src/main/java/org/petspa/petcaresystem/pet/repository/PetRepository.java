package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
}
