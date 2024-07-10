package org.petspa.petcaresystem.boarding.service.implement;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.repository.AppointmentRepository;
import org.petspa.petcaresystem.boarding.mapper.BoardingMapper;
import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.boarding.model.request.CreateBoardingRequest;
import org.petspa.petcaresystem.boarding.model.request.UpdateBoardingRequest;
import org.petspa.petcaresystem.boarding.model.response.BoardingResponse;
import org.petspa.petcaresystem.boarding.repository.BoardingRepository;
import org.petspa.petcaresystem.boarding.service.BoardingService;
import org.petspa.petcaresystem.boarding_detail.model.BoardingDetail;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
import org.petspa.petcaresystem.shelter.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class BoardingServiceImpl implements BoardingService{

    @Autowired
    BoardingRepository boardingRepository;
    AppointmentRepository appointmentRepository;
    ShelterRepository shelterRepository;

    @Override
    public ResponseEntity<ResponseObj> ViewAllBoarding() {
        try {
            List<BoardingAppointment> boardingList = boardingRepository.findAll();
            if (boardingList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Boarding List is empty")
                        .data(null)
                        .build();
                return ResponseEntity.ok().body(responseObj);
            }
            List<BoardingResponse> boardingResponseList = new ArrayList<>();
            for (BoardingAppointment boardingAppointment : boardingList) {
                BoardingResponse boardingResponse = BoardingMapper.toBoardingResponse(boardingAppointment);
                boardingResponseList.add(boardingResponse);
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Load Department Successfully")
                    .data(boardingResponseList)
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
    public ResponseEntity<ResponseObj> ViewABoardingbyAppointmentId(Long appointment_id) {
        try {
            Appointment appointment = appointmentRepository.getReferenceById(appointment_id);
            List<Appointment> appointmentList = appointmentRepository.findAll();
            for (Appointment appointment1 : appointmentList) {
                if (appointment1.getStatus().equals(Status.ACTIVE) && appointment1.equals(appointment)) {
                    if (appointment1.getBoardingAppointment() == null) {
                        ResponseObj responseObj = ResponseObj.builder()
                                .message("Boarding does not exist")
                                .data(null)
                                .build();
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
                    } else {
                        BoardingAppointment boarding = appointment1.getBoardingAppointment();
                        BoardingResponse boardingResponse  = BoardingMapper.toBoardingResponse(boarding);
                        ResponseObj responseObj = ResponseObj.builder()
                                .message("Load Boarding Successfully")
                                .data(boardingResponse)
                                .build();
                        return ResponseEntity.ok().body(responseObj);
                    }
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Appointment not found")
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
    public ResponseEntity<ResponseObj> CreateBoarding(CreateBoardingRequest boardingRequest) {
        try {
            BoardingAppointment boarding = new BoardingAppointment();
            List<Appointment> appointmentList = appointmentRepository.findAll();
            for (Appointment appointment1 : appointmentList) {
                if (appointment1.equals(boardingRequest.getAppointment())
                        && appointment1.getStatus().equals(Status.ACTIVE)) {

                    boarding.setAppointment(appointment1);

                    BoardingDetail boardingDetail = boardingRequest.getBoardingDetail();
                    Collection<BoardingDetail> boardingDetailList = new ArrayList<>();
                    boardingDetailList.add(boardingDetail);
                    boarding.setBoardingDetail(boardingDetailList);

                    List<Shelter> shelterList = shelterRepository.findAll();
//                    Collection<Shelter> shelters = new ArrayList<>();
                    for (Shelter shelter : shelterList) {
                        if (shelter.getStatus().equals(Status.EMPTY)) {
                            boarding.setShelter( shelter);
//                            boarding.setShelter((Collection<Shelter>) shelter);
                        }
                    }
//                    boarding.setShelter(shelter);
                    boarding.setBoardingTime(LocalDateTime.now());
                    boarding.setStatus(Status.ACTIVE);

                    BoardingAppointment createBoarding = boardingRepository.save(boarding);
                    BoardingResponse boardingResponse = BoardingMapper.toBoardingResponse(createBoarding);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Create Boarding Successfully")
                            .data(boardingResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Appointment not found")
                    .data(null)
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
    public ResponseEntity<ResponseObj> UpdateBoarding(Long boarding_id, UpdateBoardingRequest boardingRequest) {
        try {
            BoardingAppointment boarding = boardingRepository.getReferenceById(boarding_id);
            Collection<BoardingAppointment> boardingList = boardingRepository.findAll();
            for (BoardingAppointment boarding1 : boardingList) {
                if (boarding1.equals(boarding)&& boarding1.getStatus().equals(Status.ACTIVE)) {

                    Collection<BoardingDetail> boardingDetails = boardingRequest.getBoardingDetail();
                    Collection<BoardingDetail> boardingDetailList = boarding1.getBoardingDetail();
                    for (BoardingDetail boardingDetail1 : boardingDetails) {
                        boardingDetailList.add(boardingDetail1);
                    }
                    boarding.setBoardingDetail(boardingDetailList);

                    List<Shelter> shelterList = shelterRepository.findAll();
                    for (Shelter shelter : shelterList) {
                        if (shelter.equals(boardingRequest.getShelter()) && shelter.getStatus().equals(Status.EMPTY)) {
                            Shelter shelterUpdate = boarding1.getShelter();
                            shelterUpdate.setStatus(Status.EMPTY);
                            shelterRepository.save(shelterUpdate);
                            boarding1.setShelter(shelter);
//                            boarding1.setShelter((Collection<Shelter>) shelter);
                        }
                    }

                    BoardingAppointment updateBoarding = boardingRepository.save(boarding);
                    BoardingResponse boardingResponse = BoardingMapper.toBoardingResponse(updateBoarding);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Update Boarding Successfully")
                            .data(boardingResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Boarding not found")
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
    public ResponseEntity<ResponseObj> DeleteBoarding(Long boarding_id) {
        try {
            BoardingAppointment boarding = boardingRepository.getReferenceById(boarding_id);
            Collection<BoardingAppointment> boardingList = boardingRepository.findAll();
            for (BoardingAppointment boarding1 : boardingList) {
                if (boarding1.equals(boarding)&& boarding1.getStatus().equals(Status.ACTIVE)) {

                    boarding1.setStatus(Status.INACTIVE);

                    boardingRepository.save(boarding1);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Delete Boarding Successfully")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Boarding not found")
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
    public ResponseEntity<ResponseObj> RestoreBoarding(Long boarding_id) {
        try {
            BoardingAppointment boarding = boardingRepository.getReferenceById(boarding_id);
            Collection<BoardingAppointment> boardingList = boardingRepository.findAll();
            for (BoardingAppointment boarding1 : boardingList) {
                if (boarding1.equals(boarding)&& boarding1.getStatus().equals(Status.ACTIVE)) {

                    boarding1.setStatus(Status.ACTIVE);

                    BoardingAppointment restoreBoarding = boardingRepository.save(boarding1);
                    BoardingResponse boardingResponse = BoardingMapper.toBoardingResponse(restoreBoarding);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Restore Boarding Successfully")
                            .data(boardingResponse)
                            .build();
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
                }
            }

            ResponseObj responseObj = ResponseObj.builder()
                    .message("Boarding not exist")
                    .data(null)
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
