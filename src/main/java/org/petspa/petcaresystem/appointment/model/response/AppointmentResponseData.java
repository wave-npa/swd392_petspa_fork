package org.petspa.petcaresystem.appointment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponseData {
    private Long appointmentId;
    private Status status;
    private LocalDate create_date;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long bookedDoctorId;
    private Long bookedServiceId;
    private Long petId;
    private Long reviewId;
    private Long boardingAppointmentId;
    private Long userOrderId;
}
