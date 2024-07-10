package org.petspa.petcaresystem.authenuser.repository;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import org.petspa.petcaresystem.role.model.Role;


@Repository
@EnableJpaRepositories
public interface AuthenUserRepository extends JpaRepository<AuthenUser, Long>{
    public AuthenUser findByEmail(String email);
    public AuthenUser findByPhone(String phone);
    public List<AuthenUser> findByRole(Role role);
    public AuthenUser findByEmailAndPassword(String email, String password);
    public AuthenUser findByUserName(String userName);
    public AuthenUser findByUserId(Long userId);
    //find amount of user by join date
    @Query(value = "SELECT ALL FROM authen_user WHERE create_date BETWEEN :startTime AND :endTime", nativeQuery=true)
    public List<AuthenUser> findAllUsersWithCreateDateRange(
    @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
