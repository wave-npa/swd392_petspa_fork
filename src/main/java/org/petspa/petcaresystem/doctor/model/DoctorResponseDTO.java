package org.petspa.petcaresystem.doctor.model;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorResponseDTO {

    private Long doctor_id;
    private AuthenUser authenUser;

}
