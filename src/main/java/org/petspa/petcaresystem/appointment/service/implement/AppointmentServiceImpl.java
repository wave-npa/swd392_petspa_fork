package org.petspa.petcaresystem.appointment.service.implement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;

import org.petspa.petcaresystem.appointment.model.Appointment;
import org.petspa.petcaresystem.appointment.repository.AppointmentRepository;
import org.petspa.petcaresystem.appointment.service.AppointmentService;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;

public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Collection<Appointment> findAllAppointment() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findAppointmentById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).orElse(null);
    }

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findById(appointment.getAppointment_id()).orElse(null);
        if(appointment.getBookedDoctor().isEmpty()){
            // automatically pick doctor based on their schedule
        }
        else{
            existingAppointment.setBookedDoctor(appointment.getBookedDoctor());
        }
        existingAppointment.setBookedService(appointment.getBookedService());
        existingAppointment.setCreate_date(LocalDate.now());
        existingAppointment.setStartTime(appointment.getStartTime());
        existingAppointment.setEndTime(appointment.getEndTime());
        existingAppointment.setPet(appointment.getPet());
        return appointmentRepository.save(existingAppointment);
    }

    @Override
    public Appointment deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        appointment.setStatus(Status.INACTIVE);
        return appointmentRepository.save(appointment);
    }

}
