package org.petspa.petcaresystem.boarding.service;

import org.petspa.petcaresystem.boarding.model.request.CreateBoardingRequest;
import org.petspa.petcaresystem.boarding.model.request.UpdateBoardingRequest;
import org.petspa.petcaresystem.department.model.request.CreateDepartmentRequest;
import org.petspa.petcaresystem.department.model.request.UpdateDepartmentRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface BoardingService {
    ResponseEntity<ResponseObj> ViewAllBoarding();
    ResponseEntity<ResponseObj> ViewABoardingbyAppointmentId(Long appointment_id);
    ResponseEntity<ResponseObj> CreateBoarding(CreateBoardingRequest boardingRequest);
    ResponseEntity<ResponseObj> UpdateBoarding(Long boarding_id, UpdateBoardingRequest boardingRequest);
    ResponseEntity<ResponseObj> DeleteBoarding(Long boarding_id);
    ResponseEntity<ResponseObj> RestoreBoarding(Long boarding_id);
}
