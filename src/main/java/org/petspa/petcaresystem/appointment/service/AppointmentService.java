package org.petspa.petcaresystem.appointment.service;


import java.util.List;
import org.petspa.petcaresystem.appointment.model.request.CreateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.request.UpdateAppointmentRequestDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseDTO2;
import org.petspa.petcaresystem.appointment.model.response.AppointmentResponseInfor;
import org.petspa.petcaresystem.enums.Option;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.schedule.model.response.ResponseInfor;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {

    public AppointmentResponseDTO2 findAllAppointment();
    public AppointmentResponseDTO2 findAppointmentById(Long appointmentId);
    public AppointmentResponseInfor saveAppointment(CreateAppointmentRequestDTO createAppointmentRequestDTO, Option option, String fullName, String phone, String email);
    public AppointmentResponseInfor updateAppointment(UpdateAppointmentRequestDTO updateAppointmentRequestDTO);
    public AppointmentResponseInfor updateAppointmentStatus(Long appointmentId, Status status);
    public AppointmentResponseDTO2 findAllAppointmentByUserId(Long userId);
    public AppointmentResponseDTO2 findAppointmentByDoctorId(Long doctorId);
    public AppointmentResponseInfor deleteAppointment(Long appointmentId);
}
