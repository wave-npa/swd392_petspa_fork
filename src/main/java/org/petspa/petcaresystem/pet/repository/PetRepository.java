package org.petspa.petcaresystem.pet.repository;


import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    public Pet findByOwner(AuthenUser authenUser);
    public Pet findByPetId(Long petId);
}
