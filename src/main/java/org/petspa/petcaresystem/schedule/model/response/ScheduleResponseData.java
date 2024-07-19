package org.petspa.petcaresystem.schedule.model.response;

import lombok.*;
import org.petspa.petcaresystem.doctor.model.DoctorData;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetailData;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ScheduleResponseData {
    private Long scheduleId;
    private LocalDate date;
    private Status status;
    private DoctorData doctorData;
    private List<ScheduleDetailData> scheduleDetail;
}
