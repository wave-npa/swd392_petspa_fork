package org.petspa.petcaresystem.appointment.model.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.doctor.model.DoctorData;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.PetData;
import org.petspa.petcaresystem.serviceAppointment.model.ServicesData;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponseData2 {
    private Long appointmentId;
    private Status status;
    private LocalDate create_date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DoctorData bookedDoctor;
    private ServicesData bookedService;
    private String userName;
    private String email;
    private String phoneNumber;
    private PetData pet;
    private Long reviewId;
    private Long boardingAppointmentId;
    private Long userOrderId;
}
