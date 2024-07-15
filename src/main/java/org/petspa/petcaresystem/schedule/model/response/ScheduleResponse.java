package org.petspa.petcaresystem.schedule.model.response;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.entity.ScheduleDetail;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleResponse {
    Long scheduleId;
    LocalDate date;
    Status status;
    Doctor doctor;
    Collection<ScheduleDetail> scheduleDetail;
}
