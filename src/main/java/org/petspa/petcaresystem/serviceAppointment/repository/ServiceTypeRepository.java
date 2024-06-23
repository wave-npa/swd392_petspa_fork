package org.petspa.petcaresystem.serviceAppointment.repository;

import org.petspa.petcaresystem.serviceAppointment.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType, Long>{

}
