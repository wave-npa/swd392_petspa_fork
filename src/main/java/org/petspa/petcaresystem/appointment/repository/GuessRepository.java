package org.petspa.petcaresystem.appointment.repository;

import org.petspa.petcaresystem.appointment.model.payload.Appointment;
import org.petspa.petcaresystem.appointment.model.payload.GuessInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuessRepository extends JpaRepository<GuessInfor, Long> {
}
