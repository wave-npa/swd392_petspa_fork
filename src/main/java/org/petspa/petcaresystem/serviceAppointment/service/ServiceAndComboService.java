package org.petspa.petcaresystem.serviceAppointment.service;

import java.util.Collection;
import java.util.List;

import org.petspa.petcaresystem.serviceAppointment.model.Combo;
import org.petspa.petcaresystem.serviceAppointment.model.ServiceType;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.stereotype.Service;

@Service
public interface ServiceAndComboService {

    // Services service
    public Collection<Services> findAllService();

    public Services findServiceById(Long serviceId);

    public Services saveService(Services service);

    public Services updateService(Services service);

    public Services deleteService(Long serviceId);

    public List<Services> searchService(String searchTerm);

    //Type service
    public List<Services> searchServiceByType(String typeName);

    //Combo service
    public Collection<Combo> findAllCombo();

    public Combo findComboById(Long comboId);

    public Combo saveCombo(Combo combo);

    public Combo updateCombo(Combo combo);

    public Combo deleteCombo(Long comboId);
}
