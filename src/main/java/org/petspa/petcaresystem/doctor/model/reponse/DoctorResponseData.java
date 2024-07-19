package org.petspa.petcaresystem.doctor.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorResponseData {
    private Long doctorId;
    private String fullName;
}
