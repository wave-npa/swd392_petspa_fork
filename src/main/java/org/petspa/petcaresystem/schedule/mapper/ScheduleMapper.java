package org.petspa.petcaresystem.schedule.mapper;


import org.petspa.petcaresystem.schedule.model.entity.Schedule;
import org.petspa.petcaresystem.schedule.model.response.ScheduleResponse;

public class ScheduleMapper {
    public static ScheduleResponse toScheduleResponse(Schedule schedule){
        return ScheduleResponse.builder()
                .scheduleId(schedule.getScheduleId())
                .date(schedule.getDate())
                .doctor(schedule.getDoctor())
                .scheduleDetail(schedule.getScheduleDetail())
                .status(schedule.getStatus())
                .build();
    }
}
