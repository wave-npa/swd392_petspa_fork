package org.petspa.petcaresystem.appointment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponseDTO {
    private String message;
    private String timeStamp;
    private int statusCode;
    private HttpStatus statusValue;
    private CreateAppointmentRequestDTO data;
}
