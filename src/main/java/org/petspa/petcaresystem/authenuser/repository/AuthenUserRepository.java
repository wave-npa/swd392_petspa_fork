package org.petspa.petcaresystem.authenuser.repository;

import org.petspa.petcaresystem.authenuser.model.AuthenUser;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuthenUserRepository extends JpaRepository<AuthenUser, Long>{
    public AuthenUser findByEmail(String email);
    public AuthenUser findByPhone(Long phone);

    //find amount of user by join date
    @Query(value = "SELECT ALL FROM AuthenUser WHERE create_date BETWEEN :startTime AND :endTime")
    public List<AuthenUser> findAllUsersWithCreateDateRange(
            @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}