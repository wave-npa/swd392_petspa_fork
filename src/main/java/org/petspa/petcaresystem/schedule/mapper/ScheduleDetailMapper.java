package org.petspa.petcaresystem.schedule.mapper;

import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;
import org.petspa.petcaresystem.schedule.model.response.ScheduleDetailResponse;

public class ScheduleDetailMapper {
    public static ScheduleDetailResponse toScheduleDetailResponse(ScheduleDetail scheduleDetail){
        return ScheduleDetailResponse.builder()
                .detailId(scheduleDetail.getDetailId())
                .startTime(scheduleDetail.getStartTime())
                .endTime(scheduleDetail.getEndTime())
                .schedule(scheduleDetail.getSchedule())
                .status(scheduleDetail.getStatus())
                .build();
    }
}
