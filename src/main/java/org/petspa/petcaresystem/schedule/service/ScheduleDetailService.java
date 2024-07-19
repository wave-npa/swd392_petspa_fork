package org.petspa.petcaresystem.schedule.service;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleDetailRequestDTO;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequestDTO;
import org.petspa.petcaresystem.schedule.model.response.ResponseInfor;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public interface ScheduleDetailService {
    ScheduleResponseDTO viewSchedulebyDoctorId(Long doctor_id);

    ResponseInfor createScheduleDetail(Long scheduleId, CreateScheduleDetailRequestDTO createScheduleDetailRequestDTO);

    ResponseInfor updateScheduleDetail(Long scheduleDetailId, CreateScheduleDetailRequestDTO createScheduleDetailRequestDTO );
}
