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
    private String appointment_id;

    @ManyToOne
    private Doctor doctor_id;

    @OneToOne(mappedBy = "pet_id")
    private Pet pet_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "create_date")
    private int create_date;

    @Column(name = "appointment_time")
    private int appointment_time;

    @ManyToMany
    @JoinTable(
            name = "Appointment_Services",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Set<Services> services = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Appointment_Combo",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "Combo_id")
    )
    private Set<Combo> combos = new HashSet<>();
}
