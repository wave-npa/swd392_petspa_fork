package org.petspa.petcaresystem.appointment.service;

import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.enums.Option;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    Collection<Appointment> findAllAppointment();

    Appointment findAppointmentById(Long appointmentId);

    AppointmentResponseDTO saveAppointment(CreateAppointmentRequestDTO createAppointmentRequestDTO, Option option);

    Appointment updateAppointment(Appointment appointment);

    Appointment deleteAppointment(Long appointmentId);

}
