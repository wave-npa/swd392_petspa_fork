package org.petspa.petcaresystem.serviceAppointment.repository;

import java.util.List;

import org.petspa.petcaresystem.serviceAppointment.model.ServiceType;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long>{

    public List<ServiceType> findByTypeName(String typeName);

}
