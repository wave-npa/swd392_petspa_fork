package org.petspa.petcaresystem.appointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.petspa.petcaresystem.doctor.model.Doctor;
import org.petspa.petcaresystem.enums.Status;
import org.petspa.petcaresystem.pet.model.Pet;
import org.petspa.petcaresystem.service_and_combo.model.Combo;
import org.petspa.petcaresystem.service_and_combo.model.Services;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String appointmentId;

    @ManyToOne
    private Doctor doctorId;

    @OneToOne(mappedBy = "petId")
    private Pet petId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "createdDate")
    private int createdDate;

    @Column(name = "appointmentTime")
    private int appointmentTime;

    @OneToOne(mappedBy = "serviceId")
    private Services service;

//     @ManyToMany
//     @JoinTable(
//             name = "Appointment_Combo",
//             joinColumns = @JoinColumn(name = "appointment_id"),
//             inverseJoinColumns = @JoinColumn(name = "Combo_id")
//     )
//     private Set<Combo> combos = new HashSet<>();
}
