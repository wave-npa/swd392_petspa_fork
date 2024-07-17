package org.petspa.petcaresystem.serviceAppointment.repository;

import org.petspa.petcaresystem.serviceAppointment.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
}
