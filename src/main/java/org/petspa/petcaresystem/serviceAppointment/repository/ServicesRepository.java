package org.petspa.petcaresystem.serviceAppointment.repository;

import java.time.LocalDate;
import java.util.List;

import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.serviceAppointment.model.Services;
import org.petspa.petcaresystem.serviceAppointment.model.request.ServiceRatingRequestDTO;
import org.petspa.petcaresystem.serviceAppointment.model.request.ServiceRevenueRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services, Long> {

   @Query(value = "SELECT * FROM pet_spa.services WHERE service_name LIKE :searchTerm% ORDER BY services_name ASC", nativeQuery=true)
   public List<Services> searchByServiceName(
   @Param("searchTerm") String searchTerm);

   // @Query(value = "SELECT * FROM pet_spa.services WHERE service_name LIKE :searchTerm% AND (price BETWEEN :minPrice AND :maxPrice) AND (:status_con IS NULL OR status = :status_con) ORDER BY services_name ASC", nativeQuery=true)
   // public List<Services> searchByServiceTEST(
   // @Param("searchTerm") String searchTerm, @Param("minPrice") float minPrice, @Param("maxPrice") float maxPrice, @Param("status_con") Status status_con);

   @Query(value = "SELECT service_id FROM type_service WHERE service_type_id = :typeId", nativeQuery=true)
   public List<Long> searchByServiceType(
   @Param("typeId") String typeId);

   //Find service total revenue
   @Query(value = "SELECT s.service_id, s.service_name, SUM(s.price) AS total_revenue FROM services s JOIN service_booked sb ON s.service_id = sb.service_id JOIN appointment a ON sb.appointment_id = a.appointment_id WHERE a.status = 'FINISHED' AND (a.create_date BETWEEN :startTime AND :endTime) GROUP BY s.service_id, s.service_name ORDER BY total_revenue DESC LIMIT 5", nativeQuery = true)
   public List<ServiceRevenueRequestDTO> findServiceRevenue(
   @Param("startTime") LocalDate startTime, @Param("endTime") LocalDate endTime);

   //Find most used service
   @Query(value = "SELECT s.service_id, s.service_name, COUNT(s.service_id) AS total_revenue FROM services s JOIN service_booked sb ON s.service_id = sb.service_id JOIN appointment a ON sb.appointment_id = a.appointment_id WHERE a.status = 'FINISHED' AND (a.create_date BETWEEN :startTime AND :endTime) GROUP BY s.service_id, s.service_name ORDER BY total_revenue DESC LIMIT 5", nativeQuery = true)
   public List<ServiceRevenueRequestDTO> findMostUsedService(
   @Param("startTime") LocalDate startTime, @Param("endTime") LocalDate endTime);

   public Services findByServiceId(Long serviceId);


}
