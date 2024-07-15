package org.petspa.petcaresystem.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorData {
        private Long doctorId;
        private Long userId;
        private Long departmentId;
        private Long scheduleId;
}
