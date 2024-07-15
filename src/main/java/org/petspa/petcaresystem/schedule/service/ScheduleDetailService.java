package org.petspa.petcaresystem.schedule.service;

import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleDetailRequest;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleDetailRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ScheduleDetailService {
    ResponseEntity<ResponseObj> ViewAllScheduleDetailByScheduleId(Long schedule_id);
    ResponseEntity<ResponseObj> CreateScheduleDetail(Long schedule_id, CreateScheduleDetailRequest createScheduleDetailRequest);
    ResponseEntity<ResponseObj> UpdateScheduleDetail(Long detail_id, UpdateScheduleDetailRequest updateScheduleDetailRequest );
    ResponseEntity<ResponseObj> DeleteScheduleDetail(Long detail_id);
}
