package org.petspa.petcaresystem.serviceAppointment.model.request;

import org.petspa.petcaresystem.serviceAppointment.model.Services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRatingRequestDTO {

    private Services services;
    private float rating;

}
