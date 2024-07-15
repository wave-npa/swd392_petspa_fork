package org.petspa.petcaresystem.shelter.service.implement;

import org.petspa.petcaresystem.enums.ShelterStatus;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.shelter.mapper.ShelterMapper;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.petspa.petcaresystem.shelter.model.request.CreateShelterRequest;
import org.petspa.petcaresystem.shelter.model.request.UpdateShelterRequest;
import org.petspa.petcaresystem.shelter.model.response.ShelterResponse;
import org.petspa.petcaresystem.shelter.repository.ShelterRepository;
import org.petspa.petcaresystem.shelter.service.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ShelterServiceImpl implements ShelterService {

    @Autowired
    ShelterRepository shelterRepository;

    @Override
    public ResponseEntity<ResponseObj> ViewAllShelter() {
        try {
            List<Shelter> shelterList = shelterRepository.findAll(Sort.by(Sort.Direction.ASC, "shelterName"));
            if (shelterList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("shelter list is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            } else {
                List<ShelterResponse> shelterResponseList = new ArrayList<>();
                for (Shelter shelter : shelterList) {
                    ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(shelter);
                    shelterResponseList.add(shelterResponse);
                }
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Load shelter List Successfully")
                        .data(shelterResponseList)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            }
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
    public ResponseEntity<ResponseObj> ViewShelterAvailable() {
        try {
            List<Shelter> shelterList = shelterRepository.findAll(Sort.by(Sort.Direction.ASC, "shelterName"));
            if (shelterList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("shelter list is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            } else {
                List<ShelterResponse> shelterResponseList = new ArrayList<>();
                for (Shelter shelter : shelterList) {
                    if (shelter.getShelterStatus().equals(ShelterStatus.EMPTY)) {
                        ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(shelter);
                        shelterResponseList.add(shelterResponse);
                    }
                }
                if (shelterResponseList.isEmpty()) {
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("shelter available list is empty")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                } else {
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Load shelter List Successfully")
                            .data(shelterResponseList)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
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
    public ResponseEntity<ResponseObj> ViewShelterUsing() {
        try {
            List<Shelter> shelterList = shelterRepository.findAll(Sort.by(Sort.Direction.ASC, "shelterName"));
            if (shelterList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("shelter list is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            } else {
                List<ShelterResponse> shelterResponseList = new ArrayList<>();
                for (Shelter shelter : shelterList) {
                    if (shelter.getShelterStatus() == ShelterStatus.USING) {
                        ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(shelter);
                        shelterResponseList.add(shelterResponse);
                    }
                }
                if (shelterResponseList.isEmpty()) {
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("shelter using list is empty")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                } else {
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Load shelter List Successfully")
                            .data(shelterResponseList)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
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
    public ResponseEntity<ResponseObj> CreateShelter(String shelterName, Status status) {
        try {

            Shelter checkName = shelterRepository.findByShelterName(shelterName);
            if (checkName != null) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Shelter's name existed!")
                        .build();
                return ResponseEntity.status(HttpStatus.CONFLICT).body(responseObj);
            }

            Shelter shelter = new Shelter();
            shelter.setShelterName(shelterName);
            shelter.setShelterStatus(ShelterStatus.EMPTY);
            shelter.setStatus(status);

            Shelter createShelter = shelterRepository.save(shelter);
            ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(createShelter);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Create Shelter successfully")
                    .data(shelterResponse)
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
    public ResponseEntity<ResponseObj> UpdateShelter(Long shelter_id, String shelterName, Status status, ShelterStatus shelterStatus) {
        try {
            Shelter shelter = shelterRepository.findByShelterId(shelter_id);
            if (shelter == null) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Shelter not found!")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }

            Shelter checkName = shelterRepository.findByShelterName(shelterName);
            if(checkName != null){
                if(!shelterName.equals(shelter.getShelterName())){
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Shelter's name existed!")
                            .build();
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(responseObj);
                }
            }

            shelter.setShelterName(shelterName);

            shelter.setShelterStatus(shelterStatus);

            shelter.setStatus(status);

            Shelter updateShelter = shelterRepository.save(shelter);
            ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(updateShelter);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Update Shelter successfully")
                    .data(shelterResponse)
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
    public ResponseEntity<ResponseObj> getShelterById(Long shelterId){
        try {
            Shelter shelter = shelterRepository.findByShelterId(shelterId);
            if (shelter == null) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Shelter not found!")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }

            ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(shelter);

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Shelter found")
                    .data(shelterResponse)
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
}
