package org.petspa.petcaresystem.doctor.service.implement;

import java.util.Collection;

import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.doctor.service.DoctorService;
import org.petspa.petcaresystem.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AuthenUserRepository authenUserRepository;

    @Override
    public Collection<Doctor> findAllDoctor() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(doctor.getDoctorId()).orElse(null);
        existingDoctor.setDepartment(doctor.getDepartment());
        existingDoctor.setUser(doctor.getUser());
        return doctorRepository.save(existingDoctor);
    }

    @Override
    public Doctor deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).orElse(null);
        AuthenUser user = doctor.getUser();
        user.setStatus(Status.INACTIVE);
        doctor.setUser(user);
        return doctorRepository.save(doctor);
    }

}
