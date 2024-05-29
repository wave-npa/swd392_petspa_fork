package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
