package org.petspa.petcaresystem.serviceAppointment.service.implement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.serviceAppointment.model.Combo;
import org.petspa.petcaresystem.serviceAppointment.model.ServiceType;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.repository.ComboRepository;
import org.petspa.petcaresystem.serviceAppointment.repository.ServiceTypeRepository;
import org.petspa.petcaresystem.serviceAppointment.repository.ServicesRepository;
import org.petspa.petcaresystem.serviceAppointment.service.ServiceAndComboService;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceAndComboServiceImpl implements ServiceAndComboService{

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @Autowired
    private ComboRepository comboRepository;

    //Services service
    @Override
    public Collection<Services> findAllService() {
        return servicesRepository.findAll();
    }

    @Override
    public Services findServiceById(Long serviceId) {
        return servicesRepository.findById(serviceId).orElse(null);
    }

    @Override
    public Services saveService(Services service) {
        return servicesRepository.save(service);
    }

    @Override
    public Services updateService(Services service) {
        return servicesRepository.save(service);
    }

    @Override
    public Services deleteService(Long serviceId) {
        Services service = servicesRepository.findById(serviceId).orElse(null);
        service.setStatus(Status.INACTIVE);
        return servicesRepository.save(service);
    }

    @Override
    public List<Services> searchService(String searchTerm) {
       return servicesRepository.searchByServiceName(searchTerm);
    }

    //Type service
    @Override
    public List<Services> searchServiceByType(String typeName) {
        List<ServiceType> serviceType = serviceTypeRepository.findByTypeName(typeName);
        String typeId = String.valueOf(serviceType.get(0).getServiceTypeId());

        List<Long> serviceId = servicesRepository.searchByServiceType(typeId);

        List<Services> serviceList = new ArrayList<>();
        for(int i = 0; i<serviceId.size(); i++) {
            serviceList.add(servicesRepository.findById(serviceId.get(i)).orElse(null));
        }
        return serviceList;
    }

    //Combo service
    @Override
    public Collection<Combo> findAllCombo() {
        return comboRepository.findAll();
    }

    @Override
    public Combo findComboById(Long comboId) {
        return comboRepository.findById(comboId).orElse(null);
    }

    @Override
    public Combo saveCombo(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo updateCombo(Combo combo) {
        return comboRepository.save(combo);
    }

    @Override
    public Combo deleteCombo(Long comboId) {
        Combo combo = comboRepository.findById(comboId).orElse(null);
        combo.setStatus(Status.INACTIVE);
        return comboRepository.save(combo);
    }
}
