package org.petspa.petcaresystem.department.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;

import java.util.Collection;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentResponse {
    Long department_id;
    String departmentName;
    String address;
    Status status;
    Collection<Doctor> doctors;
}
