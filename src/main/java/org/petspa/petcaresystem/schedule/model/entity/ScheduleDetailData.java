package org.petspa.petcaresystem.schedule.model.entity;

import lombok.*;
import org.petspa.petcaresystem.enums.Status;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ScheduleDetailData {
    private Long detailId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
}
