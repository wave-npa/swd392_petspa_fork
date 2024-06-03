package org.petspa.petcaresystem.service_and_combo.service.implement;

import java.util.Collection;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.service_and_combo.model.Combo;
import org.petspa.petcaresystem.service_and_combo.model.Services;
import org.petspa.petcaresystem.service_and_combo.repository.ComboRepository;
import org.petspa.petcaresystem.service_and_combo.repository.ServicesRepository;
import org.petspa.petcaresystem.service_and_combo.service.ServiceAndComboService;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceAndComboServiceImpl implements ServiceAndComboService{

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ComboRepository comboRepository;

    @Override
    public Collection<Services> findAllService() {
        return servicesRepository.findAll();
    }

    @Override
    public Services findServiceById(String serviceId) {
        return servicesRepository.findById(serviceId).orElse(null);
    }

    @Override
    public Services saveService(Services service) {
        Integer size = servicesRepository.findAll().size() + 1;
        String id = "SR" + size.toString();
        service.setServiceId(id);
        return servicesRepository.save(service);
    }

    @Override
    public Services updateService(Services service) {
        return servicesRepository.save(service);
    }

    @Override
    public Services deleteService(String serviceId) {
        Services service = servicesRepository.findById(serviceId).orElse(null);
        service.setStatus(Status.INACTIVE);
        return servicesRepository.save(service);
    }

    @Override
    public Collection<Combo> findAllCombo() {
        return comboRepository.findAll();
    }

    @Override
    public Combo findComboById(String comboId) {
        return comboRepository.findById(comboId).orElse(null);
    }

    @Override
    public Combo saveCombo(Combo combo) {
        Integer size = comboRepository.findAll().size() + 1;
        String id = "CB" + size.toString();
        combo.setComboId(id);
        return comboRepository.save(combo);
    }

    @Override
    public Combo updateCombo(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo deleteCombo(String comboId) {
        Combo combo = comboRepository.findById(comboId).orElse(null);
        combo.setStatus(Status.INACTIVE);
        return comboRepository.save(combo);
    }
}
