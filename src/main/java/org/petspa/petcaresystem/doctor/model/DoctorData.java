package org.petspa.petcaresystem.doctor.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.department.model.entity.Departments;
import org.petspa.petcaresystem.schedule.model.Schedule;

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
