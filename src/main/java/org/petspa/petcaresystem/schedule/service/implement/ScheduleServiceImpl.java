package org.petspa.petcaresystem.schedule.service.implement;


import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.schedule.mapper.ScheduleMapper;
import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleRequest;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponse;
import org.petspa.petcaresystem.schedule.repository.ScheduleRepository;
import org.petspa.petcaresystem.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;
    private DoctorRepository doctorRepository;

    @Override
    public ResponseEntity<ResponseObj> ViewAllSchedule() {
        try {
            List<Schedule> scheduleList = scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
            if (scheduleList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            List<ScheduleResponse> scheduleResponses = new ArrayList<>();
            for (Schedule schedule : scheduleList) {
                ScheduleResponse reviewResponse = ScheduleMapper.toScheduleResponse(schedule);
                scheduleResponses.add(reviewResponse);
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Load Schedules Successfully")
                    .data(scheduleResponses)
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
    public ResponseEntity<ResponseObj> ViewSchedulebyDoctorId(Long doctor_id) {
        try {
            Doctor doctor = doctorRepository.getReferenceById(doctor_id);
            List<Schedule> scheduleDoctor = scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
            if (scheduleDoctor.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            List<ScheduleResponse> scheduleResponses = new ArrayList<>();
            for (Schedule schedule : scheduleDoctor) {
                if (schedule.getDoctor().equals(doctor)) {
                    ScheduleResponse reviewResponse = ScheduleMapper.toScheduleResponse(schedule);
                    scheduleResponses.add(reviewResponse);
                }
            }
            if (scheduleResponses.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created for this doctor yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            } else {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Load Schedules Successfully")
                        .data(scheduleResponses)
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
    public ResponseEntity<ResponseObj> CreateSchedule(Long doctor_id, CreateScheduleRequest createScheduleRequest) {
        try {
            Doctor doctor = doctorRepository.getReferenceById(doctor_id);
            List<Doctor> doctorList = doctorRepository.findAll();
            Schedule schedule = new Schedule();
            for (Doctor doctor1 : doctorList) {
                if (doctor1.equals(doctor) && doctor1.getUser().getStatus().equals(Status.ACTIVE)) {
                    schedule.setDate(createScheduleRequest.getDate());
                    schedule.setDoctor(doctor1);
                    schedule.setStatus(Status.ACTIVE);
                    scheduleRepository.save(schedule);
                    ScheduleResponse reviewResponse = ScheduleMapper.toScheduleResponse(schedule);
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Create Schedule Successfully")
                            .data(reviewResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                .message("Doctor not found")
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
    public ResponseEntity<ResponseObj> UpdateSchedule(Long schedule_id, UpdateScheduleRequest updateScheduleRequest) {
        try {
            Schedule updateschedule = scheduleRepository.getReferenceById(schedule_id);
            List<Schedule> scheduleDoctor = scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
            if (scheduleDoctor.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (Schedule schedule : scheduleDoctor) {
                if (schedule.equals(updateschedule) && schedule.getStatus().equals(Status.ACTIVE)) {
                    schedule.setDate(updateScheduleRequest.getDate());

                    Doctor doctor = doctorRepository.getReferenceById(updateScheduleRequest.getDoctor_id());
                    schedule.setDoctor(doctor);

                    scheduleRepository.save(schedule);
                    ScheduleResponse reviewResponse = ScheduleMapper.toScheduleResponse(schedule);
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Update Schedule Successfully")
                            .data(reviewResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Schedule not found")
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
    public ResponseEntity<ResponseObj> DeleteSchedule(Long schedule_id) {
        try {
            Schedule restoreSchedule = scheduleRepository.getReferenceById(schedule_id);
            List<Schedule> scheduleList = scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
            if (scheduleList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (Schedule schedule : scheduleList) {
                if (schedule.equals(restoreSchedule) && schedule.getStatus().equals(Status.INACTIVE)) {
                    schedule.setStatus(Status.ACTIVE);
                    scheduleRepository.save(schedule);
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Delete Schedule Successfully")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Schedule not found")
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
    public ResponseEntity<ResponseObj> RestoreSchedule(Long schedule_id) {
        try {
            Schedule restoreSchedule = scheduleRepository.getReferenceById(schedule_id);
            List<Schedule> scheduleList = scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
            if (scheduleList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (Schedule schedule : scheduleList) {
                if (schedule.equals(restoreSchedule) && schedule.getStatus().equals(Status.INACTIVE)) {
                    schedule.setStatus(Status.ACTIVE);
                    scheduleRepository.save(schedule);

                    ScheduleResponse scheduleResponse = ScheduleMapper.toScheduleResponse(schedule);
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Restore Schedule Successfully")
                            .data(scheduleResponse)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Schedule not found")
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
