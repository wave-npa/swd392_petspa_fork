package org.petspa.petcaresystem.service_and_combo.service;

import java.util.Collection;

import org.petspa.petcaresystem.service_and_combo.model.Services;
import org.springframework.stereotype.Service;

@Service
public interface ServiceAndComboService {

    public Collection<Services> findAll();

    public Services findById()
}
