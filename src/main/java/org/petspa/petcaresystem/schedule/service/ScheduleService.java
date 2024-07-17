package org.petspa.petcaresystem.schedule.service;

import org.petspa.petcaresystem.pet.model.request.UpdatePetRequest;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleService {
    ResponseEntity<ResponseObj> ViewAllSchedule();
    ResponseEntity<ResponseObj> ViewSchedulebyDoctorId(Long doctor_id);
    ResponseEntity<ResponseObj> CreateSchedule(Long doctor_id, CreateScheduleRequest createScheduleRequest);
    ResponseEntity<ResponseObj> UpdateSchedule(Long schedule_id, UpdateScheduleRequest updateScheduleRequest );
    ResponseEntity<ResponseObj> DeleteSchedule(Long schedule_id);
    ResponseEntity<ResponseObj> RestoreSchedule(Long schedule_id);
}
