package org.petspa.petcaresystem.appointment.repository;


import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public Appointment findByAppointmentId(Long appointmentId);
    public Appointment findByUserOrder(UserOrder userOrder);

}
