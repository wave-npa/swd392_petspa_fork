package org.petspa.petcaresystem.appointment.repository;


import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.order.model.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    public Appointment findByAppointmentId(Long appointmentId);
    public Appointment findByUserOrder(UserOrder userOrder);
<<<<<<< HEAD
=======

>>>>>>> backup-repo/main
    @Query(value = "SELECT appointment_id FROM pet_spa.doctor_booked WHERE doctor_id = :doctor_id", nativeQuery=true)
    public List<Long> findByDoctorId(@Param("doctor_id") Long doctorId);
}
