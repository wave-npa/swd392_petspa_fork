package org.petspa.petcaresystem.boarding.mapper;

import org.petspa.petcaresystem.boarding.model.entity.BoardingAppointment;
import org.petspa.petcaresystem.boarding.model.response.BoardingResponse;
import org.petspa.petcaresystem.department.model.entity.Departments;
import org.petspa.petcaresystem.department.model.response.DepartmentResponse;

public class BoardingMapper {
    public static BoardingResponse toBoardingResponse(BoardingAppointment Boarding){
        return BoardingResponse.builder()
                .boardingId(Boarding.getBoardingId())
                .appointment(Boarding.getAppointment())
                .boardingTime(Boarding.getBoardingTime())
                .boardingDetail(Boarding.getBoardingDetail())
                .shelter(Boarding.getShelter())
                .status(Boarding.getStatus())
                .build();
    }
}
