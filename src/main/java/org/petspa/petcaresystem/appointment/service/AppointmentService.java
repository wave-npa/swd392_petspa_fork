package org.petspa.petcaresystem.appointment.service;

import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    Collection<Appointment> findAllAppointment();

    Appointment findAppointmentById(Long appointmentId);

    AppointmentResponseDTO saveAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    Appointment deleteAppointment(Long appointmentId);

}
