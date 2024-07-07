package org.petspa.petcaresystem.appointment.service;

import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    Collection<Appointment> findAllAppointment();

    Appointment findAppointmentById(Long appointmentId);

    Appointment saveAppointment(Appointment appointment);

    Appointment updateAppointment(Appointment appointment);

    Appointment deleteAppointment(Long appointmentId);

}
