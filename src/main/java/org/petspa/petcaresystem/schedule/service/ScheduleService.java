package org.petspa.petcaresystem.schedule.service;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequestDTO;
import org.petspa.petcaresystem.schedule.model.response.ResponseInfor;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ScheduleService {
    ScheduleResponseDTO viewAllSchedule();
    ScheduleResponseDTO viewSchedulebyDoctorId(Long doctorId);
    ResponseInfor createSchedule(CreateScheduleRequestDTO createScheduleRequest);
    ResponseInfor updateSchedule(Long scheduleId, CreateScheduleRequestDTO createScheduleRequestDTO);
}
