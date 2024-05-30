package org.petspa.petcaresystem.pet.repository;


import org.petspa.petcaresystem.pet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
}
