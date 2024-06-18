package org.petspa.petcaresystem.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.entity.Pet;
import org.petspa.petcaresystem.service_and_combo.model.Services;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Appointment")
public class Appointment implements Serializable{

    @Id
    private String appointmentId;

    private Doctor doctor;

    private Pet pet;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "appointment_time")
    private LocalDateTime appointment_time;

    private Services services;

//    @ManyToOne
//    @JsonIgnore
    private Services Combo_id;
}
