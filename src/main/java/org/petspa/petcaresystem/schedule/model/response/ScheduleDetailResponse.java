package org.petspa.petcaresystem.schedule.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.entity.Schedule;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleDetailResponse {
    Long detailId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Status status;
    Schedule schedule;
}
