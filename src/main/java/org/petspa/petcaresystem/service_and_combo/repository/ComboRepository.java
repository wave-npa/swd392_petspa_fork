package org.petspa.petcaresystem.service_and_combo.repository;

import org.petspa.petcaresystem.service_and_combo.model.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComboRepository extends JpaRepository<Combo, String> {
}
