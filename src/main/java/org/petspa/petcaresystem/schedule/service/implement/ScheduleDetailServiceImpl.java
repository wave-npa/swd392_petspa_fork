package org.petspa.petcaresystem.schedule.service.implement;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.response.ResponseObj;
import org.petspa.petcaresystem.schedule.mapper.ScheduleDetailMapper;
import org.petspa.petcaresystem.schedule.mapper.ScheduleMapper;
import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleDetailRequest;
import org.petspa.petcaresystem.schedule.model.request.CreateScheduleRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleDetailRequest;
import org.petspa.petcaresystem.schedule.model.request.UpdateScheduleRequest;
import org.petspa.petcaresystem.schedule.model.response.ScheduleDetailResponse;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponse;
import org.petspa.petcaresystem.schedule.repository.ScheduleDetailRepository;
import org.petspa.petcaresystem.schedule.repository.ScheduleRepository;
import org.petspa.petcaresystem.schedule.service.ScheduleDetailService;
import org.petspa.petcaresystem.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleDetailServiceImpl implements ScheduleDetailService {

    @Autowired
    private ScheduleDetailRepository scheduleDetailRepository;
    private ScheduleRepository scheduleRepository;

    @Override
    public ResponseEntity<ResponseObj> ViewAllScheduleDetailByScheduleId(Long schedule_id) {
        try {
            Schedule viewschedule = scheduleRepository.getReferenceById(schedule_id);
            List<Schedule> scheduleDoctor = scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
            if (scheduleDoctor.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            List<ScheduleDetail> scheduleDetails = scheduleDetailRepository.findAll();
            List<ScheduleDetailResponse> scheduleDetailResponses = new ArrayList<>();
            for (Schedule schedule : scheduleDoctor) {
                if (schedule.equals(viewschedule) && schedule.getStatus().equals(Status.ACTIVE)) {
                    for (ScheduleDetail scheduleDetail : scheduleDetails) {
                        ScheduleDetailResponse scheduleDetailResponse = ScheduleDetailMapper
                                .toScheduleDetailResponse(scheduleDetail);
                        scheduleDetailResponses.add(scheduleDetailResponse);
                    }
                }
            }
            if (scheduleDetailResponses.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule detail created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            } else {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("Load Schedule Details Successfully")
                        .data(scheduleDetailResponses)
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
    public ResponseEntity<ResponseObj> CreateScheduleDetail(Long schedule_id, CreateScheduleDetailRequest createScheduleDetailRequest) {
        try {
            Schedule createforschedule = scheduleRepository.getReferenceById(schedule_id);
            List<Schedule> scheduleDoctor = scheduleRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));
            if (scheduleDoctor.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            ScheduleDetail scheduleDetail = new ScheduleDetail();
            for (Schedule schedule : scheduleDoctor) {
                if (schedule.equals(createforschedule) && schedule.getStatus().equals(Status.ACTIVE)) {

                    scheduleDetail.setStartTime(createScheduleDetailRequest.getStartTime());
                    scheduleDetail.setEndTime(createScheduleDetailRequest.getEndTime());
                    scheduleDetail.setSchedule(schedule);
                    scheduleDetail.setStatus(Status.ACTIVE);

                    scheduleDetailRepository.save(scheduleDetail);
                    ScheduleDetailResponse scheduleDetailResponse = ScheduleDetailMapper
                            .toScheduleDetailResponse(scheduleDetail);

                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Create Schedule Detail Successfully")
                            .data(scheduleDetailResponse)
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
    public ResponseEntity<ResponseObj> UpdateScheduleDetail(Long detail_id, UpdateScheduleDetailRequest updateScheduleDetailRequest) {
        try {
            ScheduleDetail updatescheduleDetail = scheduleDetailRepository.getReferenceById(detail_id);
            List<ScheduleDetail> scheduleDetailList = scheduleDetailRepository.findAll();
            if (scheduleDetailList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule details created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (ScheduleDetail scheduleDetail : scheduleDetailList) {
                if (scheduleDetail.equals(updatescheduleDetail) && scheduleDetail.getStatus().equals(Status.ACTIVE)) {

                    scheduleDetail.setStartTime(updateScheduleDetailRequest.getStartTime());
                    scheduleDetail.setEndTime(updateScheduleDetailRequest.getEndTime());

                    Schedule schedule = scheduleRepository.getReferenceById(updateScheduleDetailRequest.getSchedule_id());
                    scheduleDetail.setSchedule(schedule);

                    scheduleDetail.setStatus(Status.ACTIVE);

                    scheduleDetailRepository.save(scheduleDetail);
                    ScheduleDetailResponse scheduleDetailResponse = ScheduleDetailMapper
                            .toScheduleDetailResponse(scheduleDetail);
                    ResponseObj responseObj = ResponseObj.builder()
                        .message("Update Schedule Detail Successfully")
                        .data(scheduleDetailResponse)
                        .build();
                    return ResponseEntity.ok().body(responseObj);
            }
        }
        ResponseObj responseObj = ResponseObj.builder()
                .message("Schedule detail not found")
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
    public ResponseEntity<ResponseObj> DeleteScheduleDetail(Long detail_id) {
        try {
            ScheduleDetail updatescheduleDetail = scheduleDetailRepository.getReferenceById(detail_id);
            List<ScheduleDetail> scheduleDetailList = scheduleDetailRepository.findAll();
            if (scheduleDetailList.isEmpty()) {
                ResponseObj responseObj = ResponseObj.builder()
                        .message("There are no Schedule details created yet")
                        .data(null)
                        .build();
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseObj);
            }
            for (ScheduleDetail scheduleDetail : scheduleDetailList) {
                if (scheduleDetail.equals(updatescheduleDetail) && scheduleDetail.getStatus().equals(Status.ACTIVE)) {

                    scheduleDetail.setStatus(Status.INACTIVE);

                    scheduleDetailRepository.save(scheduleDetail);
                    ResponseObj responseObj = ResponseObj.builder()
                            .message("Update Schedule Detail Successfully")
                            .data(null)
                            .build();
                    return ResponseEntity.ok().body(responseObj);
                }
            }
            ResponseObj responseObj = ResponseObj.builder()
                    .message("Schedule detail not found")
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
