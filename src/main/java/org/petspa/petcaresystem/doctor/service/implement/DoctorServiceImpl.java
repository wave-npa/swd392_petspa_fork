package org.petspa.petcaresystem.doctor.service.implement;

import java.util.ArrayList;

import java.util.List;
import org.petspa.petcaresystem.authenuser.model.payload.AuthenUser;
import org.petspa.petcaresystem.authenuser.repository.AuthenUserRepository;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.doctor.model.DoctorResponseDTO;
import org.petspa.petcaresystem.doctor.model.reponse.DoctorResponseData;
import org.petspa.petcaresystem.doctor.repository.DoctorRepository;
import org.petspa.petcaresystem.doctor.service.DoctorService;
import org.petspa.petcaresystem.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AuthenUserRepository authenUserRepository;

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public List<DoctorResponseDTO> findAllDoctor() {
        List<Doctor> listDoctor = doctorRepository.findAll();
        List<DoctorResponseDTO> listResponse = new ArrayList<>();
        for(int i = 0;i<listDoctor.size();i++){
            AuthenUser authenUser = authenUserRepository.findByUserId(listDoctor.get(i).getUser().getUserId());
            DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO(listDoctor.get(i).getDoctorId(), authenUser);
            listResponse.add(doctorResponseDTO);
        }
        return listResponse;
    }

    @Override
    public AuthenUser findDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).orElse(null).getUser();
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

    @Override
    public ResponseEntity<DoctorResponseData> findDoctorByUserId(Long userId){
        AuthenUser authenUser = authenUserRepository.findByUserId(userId);
        if(authenUser == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Doctor doctor = doctorRepository.findByUser(authenUser);
        if(doctor == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        DoctorResponseData doctorResponseData = new DoctorResponseData();
        doctorResponseData.setDoctorId(doctor.getDoctorId());
        doctorResponseData.setFullName(authenUser.getFullName());

        return new ResponseEntity<>(doctorResponseData, HttpStatus.OK);
    }
}

