package org.petspa.petcaresystem.schedule.service.implement;


import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.model.DoctorData;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetailData;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequestDTO;
import org.petspa.petcaresystem.schedule.model.response.ResponseInfor;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponseData;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponseDTO;
import org.petspa.petcaresystem.schedule.repository.ScheduleDetailRepository;
import org.petspa.petcaresystem.schedule.repository.ScheduleRepository;
import org.petspa.petcaresystem.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final String format_pattern = "yyyy-MM-dd HH:mm";

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;

    @Override
    public ScheduleResponseDTO viewAllSchedule() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Get all schedule successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        List<ScheduleResponseData> scheduleResponseDataList = new ArrayList<>();

        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setMessage(message);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {
            List<Schedule> scheduleList = scheduleRepository.findAll();

            if (scheduleList.isEmpty()) {
                message = "Schedule not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setMessage(message);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ScheduleResponseDTO(responseInfor, null, null);
            }

            for (Schedule schedule : scheduleList) {
                ScheduleResponseData scheduleResponseData = new ScheduleResponseData();

                List<ScheduleDetail> scheduleDetails = scheduleDetailRepository.findBySchedule(schedule);
                List<ScheduleDetailData> scheduleDetailDataList = new ArrayList<>();
                for (ScheduleDetail scheduleDetail : scheduleDetails) {
                    ScheduleDetailData scheduleDetailData = new ScheduleDetailData();
                    scheduleDetailData.setDetailId(scheduleDetail.getDetailId());
                    scheduleDetailData.setStartTime(scheduleDetail.getStartTime());
                    scheduleDetailData.setEndTime(scheduleDetail.getEndTime());
                    scheduleDetailData.setStatus(scheduleDetail.getStatus());
                    scheduleDetailDataList.add(scheduleDetailData);
                }

                scheduleResponseData.setScheduleDetail(scheduleDetailDataList);

                // schedule
                scheduleResponseData.setDate(schedule.getDate());
                scheduleResponseData.setScheduleId(schedule.getScheduleId());
                scheduleResponseData.setStatus(schedule.getStatus());

                // doctor data
                if(schedule.getDoctor() != null) {
                    Doctor doctor = doctorRepository.findByDoctorId(schedule.getDoctor().getDoctorId());
                    DoctorData doctorData = new DoctorData();
                    doctorData.setDoctorId(doctor.getDoctorId());
                    doctorData.setUserName(doctor.getUser().getUserName());
                    doctorData.setDepartmentName(doctor.getDepartment().getDepartmentName());
                    scheduleResponseData.setDoctorData(doctorData);
                }

                scheduleResponseDataList.add(scheduleResponseData);
            }

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
            responseInfor.setMessage(message);
            responseInfor.setStatusCode(statusCode);
            responseInfor.setStatusValue(statusValue);
        }

        return new ScheduleResponseDTO(responseInfor, null, scheduleResponseDataList);
    }

    @Override
    public ScheduleResponseDTO viewSchedulebyDoctorId(Long doctorId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Get doctor's schedule successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        ScheduleResponseData scheduleResponseData = new ScheduleResponseData();

        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setMessage(message);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {
            Doctor doctor = doctorRepository.findByDoctorId(doctorId);
            if (doctor == null) {
                message = "Doctor not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setMessage(message);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ScheduleResponseDTO(responseInfor, null, null);
            }

            Schedule schedule = scheduleRepository.findByScheduleId(doctor.getSchedule().getScheduleId());
            if (schedule == null) {
                message = "Schedule not found! Doctor doesn't have any schedule!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                responseInfor.setMessage(message);
                responseInfor.setStatusCode(statusCode);
                responseInfor.setStatusValue(statusValue);
                return new ScheduleResponseDTO(responseInfor, null, null);
            }

            List<ScheduleDetail> scheduleDetails = scheduleDetailRepository.findBySchedule(schedule);
            List<ScheduleDetailData> scheduleDetailDataList = new ArrayList<>();
            for (ScheduleDetail scheduleDetail : scheduleDetails) {
                ScheduleDetailData scheduleDetailData = new ScheduleDetailData();
                scheduleDetailData.setDetailId(scheduleDetail.getDetailId());
                scheduleDetailData.setStartTime(scheduleDetail.getStartTime());
                scheduleDetailData.setEndTime(scheduleDetail.getEndTime());
                scheduleDetailData.setStatus(scheduleDetail.getStatus());
                scheduleDetailDataList.add(scheduleDetailData);
            }

            scheduleResponseData.setScheduleDetail(scheduleDetailDataList);

            // schedule
            scheduleResponseData.setDate(schedule.getDate());
            scheduleResponseData.setScheduleId(schedule.getScheduleId());
            scheduleResponseData.setStatus(schedule.getStatus());

            // doctor data
            DoctorData doctorData = new DoctorData();
            doctorData.setDoctorId(doctor.getDoctorId());
            doctorData.setUserName(doctor.getUser().getUserName());
            doctorData.setDepartmentName(doctor.getDepartment().getDepartmentName());
            scheduleResponseData.setDoctorData(doctorData);

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
            responseInfor.setMessage(message);
            responseInfor.setStatusCode(statusCode);
            responseInfor.setStatusValue(statusValue);
            return new ScheduleResponseDTO(responseInfor, null, null);
        }

        return new ScheduleResponseDTO(responseInfor, scheduleResponseData, null);
    }

    @Override
    @Transactional
    public ResponseInfor updateSchedule(Long scheduleId, CreateScheduleRequestDTO createScheduleRequestDTO) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format_pattern);
        String timeStamp = localDateTime.format(formatter);
        String message = "Update new schedule and detail successfully";
        int statusCode = HttpStatus.OK.value();
        HttpStatus statusValue = HttpStatus.OK;

        ResponseInfor responseInfor = new ResponseInfor();
        responseInfor.setTimeStamp(timeStamp);
        responseInfor.setMessage(message);
        responseInfor.setStatusCode(statusCode);
        responseInfor.setStatusValue(statusValue);

        try {

            // schedule
            Schedule schedule = scheduleRepository.findByScheduleId(scheduleId);
            if (schedule == null) {
                message = "Schedule not found!";
                statusCode = HttpStatus.NOT_FOUND.value();
                statusValue = HttpStatus.NOT_FOUND;
                return new ResponseInfor(message, timeStamp, statusCode, statusValue);
            }

            schedule.setDate(createScheduleRequestDTO.getDate());
            schedule.setStatus(createScheduleRequestDTO.getStatus());
            scheduleRepository.save(schedule);

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseInfor(message, timeStamp, statusCode, statusValue);
    }

    @Override
    public ResponseInfor createSchedule(CreateScheduleRequestDTO createScheduleRequest) {
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

            // schedule
            Schedule schedule = new Schedule();
            schedule.setDate(createScheduleRequest.getDate());
            schedule.setStatus(createScheduleRequest.getStatus());
            scheduleRepository.save(schedule);

        } catch (Exception e) {
            message = "Something went wrong, server error!";
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            statusValue = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseInfor(message, timeStamp, statusCode, statusValue);
    }
}
