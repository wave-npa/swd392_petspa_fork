package org.petspa.petcaresystem.appointment.repository;


import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public Appointment findByAppointmentId(Long appointmentId);

}
