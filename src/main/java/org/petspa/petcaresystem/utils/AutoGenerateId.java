package org.petspa.petcaresystem.utils;

import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AutoGenerateId {

    

    
    public static String generatePetId() {
        
        PetRepository petRepository = null;
        
        List<Pet> petList = petRepository.findAll();
        Pet lastPet = petList.get(petList.size());

        if (lastPet != null && lastPet.getPet_id() != null) {
            int lastNumber = Integer.parseInt(lastPet.getPet_id().substring(3));
            return "pet" + String.format("%04d", lastNumber + 1);
        } else {
            return "pet0001";
        }
    }
}
