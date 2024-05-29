package org.petspa.petcaresystem.repository;

import org.petspa.petcaresystem.model.entity.Combo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface ComboRepository extends JpaRepository<Combo, Integer> {
}
