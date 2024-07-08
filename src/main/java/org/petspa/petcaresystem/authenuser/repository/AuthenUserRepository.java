package org.petspa.petcaresystem.authenuser.repository;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.role.model.Role;
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
    public AuthenUser findByEmailAndPassword(String email, String password);
    public AuthenUser findByRole(Role role);
    public AuthenUser findByUserName(String userName);
    public AuthenUser findByUserId(Long userId);
    //find amount of user by join date
//    @Query("SELECT ALL FROM AuthenUser WHERE create_date BETWEEN :startTime AND :endTime")
//    public List<AuthenUser> findAllUsersWithCreateDateRange(
//            @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
