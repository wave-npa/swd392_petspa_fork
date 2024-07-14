package org.petspa.petcaresystem.serviceAppointment.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.petspa.petcaresystem.enums.Gender;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.serviceAppointment.model.Combo;
import org.petspa.petcaresystem.serviceAppointment.model.ServiceType;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.model.request.ServiceRatingRequestDTO;
import org.petspa.petcaresystem.serviceAppointment.model.request.ServiceRevenueRequestDTO;
import org.petspa.petcaresystem.serviceAppointment.model.response.RevenueResponseAPI;
import org.petspa.petcaresystem.serviceAppointment.model.response.ServiceResponseAPI;
import org.springframework.data.repository.query.Param;
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

    public ServiceResponseAPI searchServiceTEST(String searchTerm, String typeList, float minPrice, float maxPrice, Status status, String orderBy, String order);

    public ServiceResponseAPI findAverageRatingService(Long service_id);

    public RevenueResponseAPI findServiceRevenue(LocalDate startTime, LocalDate endTime);

    public RevenueResponseAPI findMostUsedService(LocalDate startTime, LocalDate endTime);

    //Type service
    public List<Services> searchServiceByType(String typeName);

    public Collection<ServiceType> findAllServiceType();

    public ServiceType saveServiceType(ServiceType serviceType);

    //Combo service
    public Collection<Combo> findAllCombo();

    public Combo findComboById(Long comboId);

    public Combo saveCombo(Combo combo);

    public Combo updateCombo(Combo combo);

    public Combo deleteCombo(Long comboId);

}
