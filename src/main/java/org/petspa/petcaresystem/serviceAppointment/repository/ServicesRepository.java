package org.petspa.petcaresystem.serviceAppointment.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

    @Query("SELECT ALL FROM Services WHERE service_name LIKE :searchTerm% ORDER BY services_name ASC")
    public List<Services> searchByServiceName(
    @Param("searchTerm") String searchTerm);

    @Query("SELECT service_id FROM type_service WHERE service_type_id = :typeId")
    public List<Long> searchByServiceType(
    @Param("typeId") String typeId);

}
