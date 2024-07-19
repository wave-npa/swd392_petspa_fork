package org.petspa.petcaresystem.doctor.repository;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    public Doctor findByDoctorId(Long doctorId);
    public Doctor findByUser(AuthenUser authenUser);
}
