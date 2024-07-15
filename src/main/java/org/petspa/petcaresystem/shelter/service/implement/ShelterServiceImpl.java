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
public class ShelterServiceImpl implements ShelterService{

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
                }else {
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
                }else {
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
    public ResponseEntity<ResponseObj> CreateShelter(CreateShelterRequest shelterRequest) {
        try {
            Shelter shelter = new Shelter();
            shelter.setShelterName(shelterRequest.getShelterName());
            shelter.setShelterStatus(ShelterStatus.EMPTY);
            shelter.setStatus(Status.ACTIVE);

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
    public ResponseEntity<ResponseObj> UpdateShelter(Long shelter_id, UpdateShelterRequest shelterRequest) {
        try {
            Shelter shelterUpdate = shelterRepository.getReferenceById(shelter_id);
            Collection<Shelter> shelterList = shelterRepository.findAll();
            for (Shelter shelter : shelterList) {
                if (shelter.equals(shelterUpdate) && shelter.getStatus().equals(Status.ACTIVE)) {
                    shelter.setShelterName(shelterRequest.getShelterName());

                    shelter.setShelterStatus(shelterRequest.getShelterStatus());

                    Shelter updateShelter = shelterRepository.save(shelter);
                    ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(updateShelter);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Update Shelter successfully")
                            .data(shelterResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Shelter not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
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
    public ResponseEntity<ResponseObj> DeleteShelter(Long shelter_id) {
        try {
            Shelter shelterDelete = shelterRepository.getReferenceById(shelter_id);
            Collection<Shelter> shelterList = shelterRepository.findAll();
            for (Shelter shelter : shelterList) {
                if (shelter.equals(shelterDelete) && shelter.getStatus().equals(Status.ACTIVE)) {
                    shelter.setStatus(Status.INACTIVE);

                    shelterRepository.save(shelter);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Delete Shelter successfully")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Shelter not found")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
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
    public ResponseEntity<ResponseObj> RestoreShelter(Long shelter_id) {
        try {
            Shelter shelterRestore = shelterRepository.getReferenceById(shelter_id);
            Collection<Shelter> shelterList = shelterRepository.findAll();
            for (Shelter shelter : shelterList) {
                if (shelter.equals(shelterRestore) && shelter.getStatus().equals(Status.INACTIVE)) {

                    shelter.setStatus(Status.ACTIVE);

                    Shelter restoreShelter = shelterRepository.save(shelter);
                    ShelterResponse shelterResponse = ShelterMapper.toShelterResponse(restoreShelter);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Restore Shelter successfully")
                            .data(shelterResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Shelter not exist")
                    .data(null)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
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
