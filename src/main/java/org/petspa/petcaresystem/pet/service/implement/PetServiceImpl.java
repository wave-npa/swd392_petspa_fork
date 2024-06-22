package org.petspa.petcaresystem.pet.service.implement;

import org.petspa.petcaresystem.authenuser.model.entity.AuthenUser;
import org.petspa.petcaresystem.authenuser.model.response.ResponseObj;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.enums.Species;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.mapper.PetMapper;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.pet.model.request.CreatePetRequest;
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
import org.petspa.petcaresystem.utils.AutoGenerateId;
import java.util.List;
import java.util.Optional;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetRepository petRepository;
    @Autowired
    AuthenUserRepository userRepository;

    @Override
    public ResponseEntity<ResponseObj> CreatePetProflie(Long cus_id, CreatePetRequest petRequest) {

        try {
            AuthenUser customer = userRepository.findById(cus_id).orElse(null);

            if (customer.getStatus() != Status.ACTIVE || customer.equals(null)) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Customer not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            Pet pet = new Pet();

            pet.setPet_name(petRequest.getPet_name());

            if (petRequest.getAge() >= 0 && petRequest.getAge() < 30) {
                pet.setAge(petRequest.getAge());
            }
            pet.setGender(petRequest.getGender());

            pet.setSpecies(petRequest.getSpecies());

            pet.setType_of_species(petRequest.getType_of_species());

            pet.setStatus(Status.ACTIVE);

            pet.setCustomer_id(customer);

            Pet createpet = petRepository.save(pet);

            PetResponse petResponse = PetMapper.toPetResponse(createpet);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Create Pet Profile Successfully")
                    .data(petResponse)
                    .build();
            return ResponseEntity.ok().body(responseObj);
        } catch (Exception e) {
            e.printStackTrace();
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Fail to load")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseObj);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseObj> UpdatePetProflie(Long pet_id, UpdatePetRequest petRequest) {
        try {
            Pet pet = petRepository.findById(pet_id).orElse(null);

            if (pet.getPet_id().equals(null)) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Pet not found")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }

            pet.setPet_name(petRequest.getPet_name());

            if (petRequest.getAge() >= 0 && petRequest.getAge() < 30) {
                pet.setAge(petRequest.getAge());
            }

            pet.setGender(petRequest.getGender());

            pet.setSpecies(petRequest.getSpecies());

            pet.setType_of_species(petRequest.getType_of_species());

            pet.setStatus(petRequest.getStatus());

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
