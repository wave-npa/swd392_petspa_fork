package org.petspa.petcaresystem.schedule.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateScheduleDetailRequestDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
}
