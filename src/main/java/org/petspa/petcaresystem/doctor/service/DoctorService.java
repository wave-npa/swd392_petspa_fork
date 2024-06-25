package org.petspa.petcaresystem.doctor.service;

import java.util.Collection;
import java.util.List;

import org.petspa.petcaresystem.doctor.model.Doctor;
import org.springframework.stereotype.Service;

@Service
public interface DoctorService {

    public Collection<Doctor> findAllDoctor();

    public Doctor findDoctorById(Long doctorId);

    public Doctor saveDoctor(Doctor doctor);

    public Doctor updateDoctor(Doctor doctor);

    public Doctor deleteDoctor(Long doctorId);

}
