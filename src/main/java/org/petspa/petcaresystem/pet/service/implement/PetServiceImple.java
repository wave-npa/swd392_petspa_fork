package org.petspa.petcaresystem.pet.service.implement;

import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.pet.mapper.PetMapper;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.petspa.petcaresystem.pet.model.response.PetResponse;
import org.petspa.petcaresystem.pet.repository.MedicalRecordRepository;
import org.petspa.petcaresystem.pet.repository.PetRepository;
import org.petspa.petcaresystem.pet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PetServiceImple implements PetService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> UpdatePetProflie(String pet_id, UpdatePetRequest petRequest) {
        try {
            Optional<Pet> petOptional = petRepository.findById(pet_id).or(null);

            if (!petOptional.isPresent()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Profile not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            Pet pet = petOptional.get();
            if (!petRequest.getPet_name().equals(null)) {
                pet.setPet_name(petRequest.getPet_name());
            }

            if (petRequest.getAge() > 0) {
                pet.setAge(petRequest.getAge());
            }

            if (!petRequest.getGender().equals(null)) {
                pet.setGender(petRequest.getGender());
            }

            if (!petRequest.getSpecies().equals(null)) {
                pet.setSpecies(petRequest.getSpecies());
            }

            if (!petRequest.getType_of_species().equals(null)) {
                pet.setType_of_species(petRequest.getType_of_species());
            }

            if (!petRequest.getStatus().equals(null)) {
                pet.setStatus(petRequest.getStatus());
            }

            Pet updatepet = petRepository.save(pet);

            PetResponse petResponse = PetMapper.toPetResponse(updatepet);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Update Pet Profile Successfully")
                    .data(petResponse)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load Pet Profile")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }
}
