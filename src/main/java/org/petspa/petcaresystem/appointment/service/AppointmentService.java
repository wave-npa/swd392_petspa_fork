package org.petspa.petcaresystem.appointment.service;

import java.util.Collection;
import java.util.List;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.request.UpdateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO2;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseInfor;
import org.petspa.petcaresystem.enums.Option;
import org.petspa.petcaresystem.enums.Status;
<<<<<<< HEAD
=======
import org.petspa.petcaresystem.shelter.model.entity.Shelter;
>>>>>>> backup-repo/main
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    public AppointmentResponseDTO2 findAllAppointment();

    public AppointmentResponseDTO findAppointmentById(Long appointmentId);

<<<<<<< HEAD
    public AppointmentResponseInfor saveAppointment(CreateAppointmentRequestDTO createAppointmentRequestDTO, Option option, String phone, String email);
=======
    public AppointmentResponseInfor saveAppointment(CreateAppointmentRequestDTO createAppointmentRequestDTO, Option option, String fullName, String phone, String email);
>>>>>>> backup-repo/main

    public AppointmentResponseInfor updateAppointment(UpdateAppointmentRequestDTO updateAppointmentRequestDTO);

    public AppointmentResponseInfor updateAppointmentStatus(Long appointmentId, Status status);

    public AppointmentResponseDTO getAppointmentByUserId();
<<<<<<< HEAD
    public List<Long> findByDoctorId(Long doctor_id);


=======

    public List<Long> findByDoctorId(Long doctor_id);

>>>>>>> backup-repo/main
}
