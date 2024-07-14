package org.petspa.petcaresystem.appointment.model.request;


import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateAppointmentRequestDTO {
    private Long doctorId;
    private LocalDateTime startTime;
    private Long serviceId;
    private Long petId;
}
