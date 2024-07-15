package org.petspa.petcaresystem.appointment.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.enums.Status;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateAppointmentRequestDTO {
    private Long appointmentId;
    private Long doctorId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Status status;
    private Long petId;
    private Long serviceId;
}
