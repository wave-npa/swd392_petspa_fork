package org.petspa.petcaresystem.schedule.service.implement;


import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleDetailRequestDTO;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequestDTO;
import org.petspa.petcaresystem.schedule.model.response.ResponseInfor;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponseDTO;
import org.petspa.petcaresystem.schedule.repository.ScheduleDetailRepository;
import org.petspa.petcaresystem.schedule.repository.ScheduleRepository;
import org.petspa.petcaresystem.schedule.service.ScheduleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ScheduleDetailServiceImpl implements ScheduleDetailService {

    private static final String format_pattern = "yyyy-MM-dd HH:mm";

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;



    @Override
    public ScheduleResponseDTO viewSchedulebyDoctorId(Long doctor_id) {
        return null;
    }

    @Override
    public ResponseInfor createScheduleDetail(Long scheduleId, CreateScheduleDetailRequestDTO createScheduleDetailRequestDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Create new schedule and detail successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setMessage(message);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {

            Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
            if(schedule == null){
                message = "Schedule not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                return new ResponseInfor(message, timeStamp, statusCode, statusValue);
            }


            // schedule detail
            ScheduleDetail scheduleDetail = new ScheduleDetail();
            scheduleDetail.setSchedule(schedule);
            scheduleDetail.setStatus(createScheduleDetailRequestDTO.getStatus());
            scheduleDetail.setStartTime(createScheduleDetailRequestDTO.getStartTime());
            scheduleDetail.setEndTime(createScheduleDetailRequestDTO.getEndTime());
            scheduleDetailRepository.save(scheduleDetail);

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseInfor(message, timeStamp, statusCode, statusValue);
    }

    @Override
    public ResponseInfor updateScheduleDetail(Long scheduleDetailId, CreateScheduleDetailRequestDTO createScheduleDetailRequestDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Create new schedule and detail successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setMessage(message);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {

            ScheduleDetail scheduleDetail = scheduleDetailRepository.findByDetailId(scheduleDetailId);
            if(scheduleDetail == null){
                message = "Schedule detail not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                return new ResponseInfor(message, timeStamp, statusCode, statusValue);
            }


            // schedule detail
            scheduleDetail.setStatus(createScheduleDetailRequestDTO.getStatus());
            scheduleDetail.setStartTime(createScheduleDetailRequestDTO.getStartTime());
            scheduleDetail.setEndTime(createScheduleDetailRequestDTO.getEndTime());
            scheduleDetailRepository.save(scheduleDetail);

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseInfor(message, timeStamp, statusCode, statusValue);
    }
}
