package org.petspa.petcaresystem.appointment.service;

import java.util.Collection;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.request.UpdateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseInfor;
import org.petspa.petcaresystem.enums.Option;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    Collection<Appointment> findAllAppointment();

    AppointmentResponseDTO findAppointmentById(Long appointmentId);

    AppointmentResponseInfor saveAppointment(CreateAppointmentRequestDTO createAppointmentRequestDTO, Option option);

    AppointmentResponseInfor updateAppointment(UpdateAppointmentRequestDTO updateAppointmentRequestDTO);

    Appointment deleteAppointment(Long appointmentId);

}
